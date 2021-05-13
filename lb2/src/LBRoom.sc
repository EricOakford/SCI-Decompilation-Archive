;;; Sierra Script 1.0 - (do not remove this comment)
(script# 17)
(include sci.sh)
(use Main)
(use PolyPath)
(use Game)
(use User)
(use System)

(public
	eRS 0
)

(procedure (localproc_0390)
	(cond 
		((< (ego x?) 0) (ego x: (+ 0 (* (ego xStep?) 2))))
		((> (ego x?) 319) (ego x: (- 319 (* (ego xStep?) 2))))
	)
)

(procedure (localproc_03d6)
	(cond 
		((< (ego y?) (curRoom horizon?)) (ego y: (+ (curRoom horizon?) (* (ego yStep?) 2))))
		((> (ego y?) 189) (ego y: (- 189 (* (ego yStep?) 2))))
	)
)

(class LBRoom of Rm
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
		picture 0
		style $ffff
		horizon 15
		controls 0
		north 0
		east 0
		south 0
		west 0
		curPic 0
		picAngle 0
		vanishingX 160
		vanishingY 0
		obstacles 0
		inset 0
	)
	
	(method (init param1 &tmp temp0 temp1 [temp2 2])
		(= number curRoomNum)
		(= perspective picAngle)
		(if picture (self drawPic: picture))
		(cond 
			((not (cast contains: ego)) 0)
			(script 0)
			((not ((User alterEgo?) edgeHit?)) 0)
			((OneOf style 11 12 13 14)
				(= temp0
					(+
						1
						(/
							(CelWide
								((User alterEgo?) view?)
								((User alterEgo?) loop?)
								((User alterEgo?) cel?)
							)
							2
						)
					)
				)
				(= temp1
					(+
						1
						(/
							(CelHigh
								((User alterEgo?) view?)
								((User alterEgo?) loop?)
								((User alterEgo?) cel?)
							)
							2
						)
					)
				)
				(switch ((User alterEgo?) edgeHit?)
					(1 ((User alterEgo?) y: 188))
					(4
						((User alterEgo?) x: (- 319 temp0))
					)
					(3
						((User alterEgo?) y: (+ horizon temp1))
					)
					(2
						((User alterEgo?) x: (+ 0 temp0))
					)
				)
				((User alterEgo?) edgeHit: 0)
			)
			(else
				(if (not argc) (= param1 0))
				(self setScript: eRS param1 prevRoomNum)
			)
		)
		(if (ego scaler?) ((ego scaler?) doit:))
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((!= curRoomNum newRoomNum) 0)
			((not (cast contains: ego)) 0)
			(
				(switch (= temp0 ((User alterEgo?) edgeHit?))
					(1 north)
					(2 east)
					(3 south)
					(4 west)
				)
				(self setScript: lRS 0 temp0)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (not (super doVerb: theVerb))
			(DftDoVerb self theVerb)
		)
	)
	
	(method (setInset)
		(theIconBar disable: 7)
		(super setInset: &rest)
	)
)

(instance lRS of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= temp1 (CelWide (ego view?) (ego loop?) (ego cel?)))
				(= temp0 (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(switch register
					(1
						(curRoom newRoom: (curRoom north?))
					)
					(3
						(ego setMotion: PolyPath (ego x?) (+ 189 temp0) self)
						(= register (curRoom south?))
					)
					(2
						(ego setMotion: PolyPath (+ 319 temp1) (ego y?) self)
						(= register (curRoom east?))
					)
					(4
						(ego setMotion: PolyPath (- 0 temp1) (ego y?) self)
						(= register (curRoom west?))
					)
				)
			)
			(1 (ego hide:) (= cycles 2))
			(2 (curRoom newRoom: register))
		)
	)
)

(instance eRS of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(= cycles 0)
				(if (user canControl:) (theGame handsOff:))
				(= temp0 (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(= temp1 (CelWide (ego view?) (ego loop?) (ego cel?)))
				(switch register
					((client north?)
						(localproc_0390)
						(ego y: (+ (curRoom horizon?) (ego yStep?)))
						(= cycles 1)
					)
					((client south?)
						(localproc_0390)
						(ego
							y: (+ 189 temp0)
							setMotion: PolyPath (ego x?) (- 189 (* (ego yStep?) 2)) self
						)
					)
					((client east?)
						(localproc_03d6)
						(ego
							x: (+ 319 (/ temp1 2))
							setMotion: PolyPath (- 319 (* (ego xStep?) 2)) (ego y?) self
						)
					)
					((client west?)
						(localproc_03d6)
						(ego
							x: (- 0 (/ temp1 2))
							setMotion: PolyPath (+ 0 (* (ego xStep?) 2)) (ego y?) self
						)
					)
					(else  (= cycles 1))
				)
			)
			(1
				(theGame handsOn: 1)
				(self dispose:)
			)
		)
	)
)
