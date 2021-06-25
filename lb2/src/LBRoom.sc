;;; Sierra Script 1.0 - (do not remove this comment)
(script# LBROOM)
(include game.sh)
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
		((< (ego x?) 0)
			(ego x: (+ 0 (* (ego xStep?) 2)))
		)
		((> (ego x?) 319)
			(ego x: (- 319 (* (ego xStep?) 2)))
		)
	)
)

(procedure (localproc_03d6)
	(cond 
		((< (ego y?) (curRoom horizon?))
			(ego y: (+ (curRoom horizon?) (* (ego yStep?) 2)))
		)
		((> (ego y?) 189)
			(ego y: (- 189 (* (ego yStep?) 2)))
		)
	)
)

(class LBRoom of Room
	
	(method (init param1 &tmp w h [temp2 2])
		(= number curRoomNum)
		(= perspective picAngle)
		(if picture
			(self drawPic: picture)
		)
		(cond 
			((not (cast contains: ego)) 0)
			(script 0)
			((not ((User alterEgo?) edgeHit?)) 0)
			((OneOf style SCROLLRIGHT SCROLLLEFT SCROLLUP SCROLLDOWN)
				(= w
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
				(= h
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
					(NORTH
						((User alterEgo?) y: 188)
					)
					(WEST
						((User alterEgo?) x: (- 319 w))
					)
					(SOUTH
						((User alterEgo?) y: (+ horizon h))
					)
					(EAST
						((User alterEgo?) x: (+ 0 w))
					)
				)
				((User alterEgo?) edgeHit: 0)
			)
			(else
				(if (not argc)
					(= param1 0)
				)
				(self setScript: eRS param1 prevRoomNum)
			)
		)
		(if (ego scaler?)
			((ego scaler?) doit:)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((!= curRoomNum newRoomNum) 0)
			((not (cast contains: ego)) 0)
			(
				(switch (= temp0 ((User alterEgo?) edgeHit?))
					(NORTH north)
					(EAST east)
					(SOUTH south)
					(WEST west)
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
		(theIconBar disable: ICON_CONTROL)
		(super setInset: &rest)
	)
)

(instance lRS of Script
	
	(method (changeState newState &tmp h w)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= w (CelWide (ego view?) (ego loop?) (ego cel?)))
				(= h (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(switch register
					(1
						(curRoom newRoom: (curRoom north?))
					)
					(3
						(ego setMotion: PolyPath (ego x?) (+ 189 h) self)
						(= register (curRoom south?))
					)
					(2
						(ego setMotion: PolyPath (+ 319 w) (ego y?) self)
						(= register (curRoom east?))
					)
					(4
						(ego setMotion: PolyPath (- 0 w) (ego y?) self)
						(= register (curRoom west?))
					)
				)
			)
			(1
				(ego hide:)
				(= cycles 2)
			)
			(2
				(curRoom newRoom: register)
			)
		)
	)
)

(instance eRS of Script
	
	(method (changeState newState &tmp h w)
		(switch (= state newState)
			(0
				(= cycles 0)
				(if (user canControl:) (theGame handsOff:))
				(= h (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(= w (CelWide (ego view?) (ego loop?) (ego cel?)))
				(switch register
					((client north?)
						(localproc_0390)
						(ego y: (+ (curRoom horizon?) (ego yStep?)))
						(= cycles 1)
					)
					((client south?)
						(localproc_0390)
						(ego
							y: (+ 189 h)
							setMotion: PolyPath (ego x?) (- 189 (* (ego yStep?) 2)) self
						)
					)
					((client east?)
						(localproc_03d6)
						(ego
							x: (+ 319 (/ w 2))
							setMotion: PolyPath (- 319 (* (ego xStep?) 2)) (ego y?) self
						)
					)
					((client west?)
						(localproc_03d6)
						(ego
							x: (- 0 (/ w 2))
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
