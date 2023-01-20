;;; Sierra Script 1.0 - (do not remove this comment)
(script# 812)
(include sci.sh)
(use Main)
(use Intrface)
(use PolyPath)
(use Motion)
(use Game)
(use User)
(use System)

(public
	eRS 0
)

(procedure (localproc_04b6)
	(cond 
		((< (ego x?) 0) (ego x: (+ 0 (* (ego xStep?) 2))))
		((> (ego x?) 319) (ego x: (- 319 (* (ego xStep?) 2))))
	)
)

(procedure (localproc_0503)
	(cond 
		((< (ego y?) (curRoom horizon?)) (ego y: (+ (curRoom horizon?) (* (ego yStep?) 2))))
		((> (ego y?) 189) (ego y: (- 189 (* (ego yStep?) 2))))
	)
)

(instance controls of Controls
	(properties)
)

(class SQRoom of Rm
	(properties
		script 0
		number 0
		timer 0
		keep 0
		initialized 0
		lookStr 0
		picture 0
		style $ffff
		horizon 0
		controls 0
		north 0
		east 0
		south 0
		west 0
		curPic 0
		picAngle 0
		vanishingX 160
		vanishingY -30000
		obstacles 0
		walkOffTop 0
	)
	
	(method (init &tmp temp0 temp1 temp2)
		(= number curRoomNum)
		(= controls controls)
		(= temp2 0)
		(= perspective picAngle)
		(if
		(and (< howFast 1) (>= 43 style) (>= style 40))
			(= temp2 1)
			(= style
				(switch style
					(12 3)
					(11 2)
					(13 5)
					(14 4)
				))
		)
		(if picture (self drawPic: picture))
		(cond 
			((not (cast contains: ego)) 0)
			(script 0)
			((or temp2 (OneOf style 11 12 13 14))
				(HandsOn)
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
						(CelHigh
							((User alterEgo?) view?)
							((User alterEgo?) loop?)
							((User alterEgo?) cel?)
						)
					)
				)
				(switch ((User alterEgo?) edgeHit?)
					(1 ((User alterEgo?) y: 188))
					(4
						((User alterEgo?) x: (- 319 temp0))
					)
					(3
						((User alterEgo?) y: (- (+ horizon temp1) 5))
					)
					(2
						((User alterEgo?) x: (+ 0 temp0))
					)
				)
				((User alterEgo?) edgeHit: 0)
			)
			(else (self setScript: eRS))
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((not (cast contains: ego)) 0)
			(
				(= temp0
					(switch ((User alterEgo?) edgeHit?)
						(1 north)
						(2 east)
						(3 south)
						(4 west)
					)
				)
				(self setScript: lRS 0 temp0)
			)
		)
	)
	
	(method (newRoom)
		(HandsOff)
		(super newRoom: &rest)
	)
)

(instance lRS of Script
	(properties)
	
	(method (dispose)
		(ego ignoreActors: 0)
		(super dispose:)
	)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1)
				(= temp1 25)
				(switch register
					((client north?)
						(if (client walkOffTop?)
							(ego
								ignoreHorizon: 1
								setMotion: PolyPath (ego x?) -1 self
							)
						else
							(curRoom newRoom: register)
						)
					)
					((client south?)
						(= temp0 (CelHigh (ego view?) (ego loop?) (ego cel?)))
						(if (IsObject (ego _head?))
							(= temp0
								(+
									temp0
									(CelHigh
										((ego _head?) view?)
										((ego _head?) loop?)
										((ego _head?) cel?)
									)
									3
								)
							)
						)
						(ego setMotion: PolyPath (ego x?) (+ 189 temp0) self)
					)
					((client east?)
						(ego setMotion: PolyPath (+ 319 temp1) (ego y?) self)
					)
					((client west?)
						(ego setMotion: PolyPath (- 0 temp1) (ego y?) self)
					)
				)
			)
			(1 (ego hide:) (= cycles 1))
			(2
				(curRoom setScript: 0 newRoom: register)
			)
		)
	)
)

(instance eRS of Script
	(properties)
	
	(method (init)
		(ego ignoreActors: 1)
		(super init: &rest)
	)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(= cycles 0)
				(HandsOff)
				(= temp0 (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(= temp1 25)
				(switch prevRoomNum
					((client north?)
						(localproc_04b6)
						(ego y: (+ (curRoom horizon?) (ego yStep?)))
						(= cycles 1)
					)
					((client south?)
						(localproc_04b6)
						(ego
							y: (+ 189 temp0)
							setMotion: nBMT (ego x?) (- 189 (* (ego yStep?) 2)) self
						)
					)
					((client east?)
						(localproc_0503)
						(ego
							x: (+ 319 (/ temp1 2))
							setMotion: nBMT (- 319 (* (ego xStep?) 2)) (ego y?) self
						)
					)
					((client west?)
						(localproc_0503)
						(ego
							x: (- 0 (/ temp1 2))
							setMotion: nBMT (+ 0 (* (ego xStep?) 2)) (ego y?) self
						)
					)
					(else  (= cycles 1))
				)
			)
			(1
				(HandsOn)
				(ego ignoreActors: 0)
				(client notify:)
				(self dispose:)
			)
		)
	)
)

(instance nBMT of MoveTo
	(properties)
	
	(method (doit)
		(super doit:)
		(if (client isBlocked:) (self moveDone:))
	)
)
