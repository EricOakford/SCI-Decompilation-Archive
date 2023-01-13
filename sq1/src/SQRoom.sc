;;; Sierra Script 1.0 - (do not remove this comment)
(script# SQROOM) ;812
(include game.sh)
(use Main)
(use Intrface)
(use PolyPath)
(use Motion)
(use Game)
(use User)
(use System)

(public
	enterRmScript 0
)

(procedure (SeeIfOffX)
	(cond 
		((< (ego x?) westEdge)
			(ego x: (+ 0 (* (ego xStep?) 2)))
		)
		((> (ego x?) eastEdge)
			(ego x: (- 319 (* (ego xStep?) 2)))
		)
	)
)

(procedure (SeeIfOffY)
	(cond 
		((< (ego y?) (curRoom horizon?))
			(ego y: (+ (curRoom horizon?) (* (ego yStep?) 2)))
		)
		((> (ego y?) southEdge)
			(ego y: (- southEdge (* (ego yStep?) 2)))
		)
	)
)

(instance roomControls of Controls
	(properties
		name "controls"
	)
)

(class SQRoom of Room
	(properties
		walkOffTop 0
	)
	
	(method (init &tmp wide high scrolling)
		(= number curRoomNum)
		(= controls roomControls)
		(= scrolling 0)
		(= perspective picAngle)
		(if (and (< howFast medium) (>= 43 style) (>= style 40))
			(= scrolling TRUE)
			(= style
				(switch style
					(SCROLLLEFT WIPERIGHT)
					(SCROLLRIGHT WIPELEFT)
					(SCROLLUP WIPEDOWN)
					(SCROLLDOWN WIPEUP)
				)
			)
		)
		(if picture
			(self drawPic: picture)
		)
		(cond 
			((not (cast contains: ego)) 0)
			(script 0)
			((or scrolling (OneOf style SCROLLRIGHT SCROLLLEFT SCROLLUP SCROLLDOWN))
				(HandsOn)
				(= wide (+ 1 (/ (CelWide ((User alterEgo?) view?) ((User alterEgo?) loop?) ((User alterEgo?) cel?)) 2)))
				(= high (+ 1 (CelHigh ((User alterEgo?) view?) ((User alterEgo?) loop?) ((User alterEgo?) cel?))))
				(switch ((User alterEgo?) edgeHit?)
					(NORTH
						((User alterEgo?) y: (- southEdge 1))
					)
					(WEST
						((User alterEgo?) x: (- eastEdge wide))
					)
					(SOUTH
						((User alterEgo?) y: (- (+ horizon high) 5))
					)
					(EAST
						((User alterEgo?) x: (+ westEdge wide))
					)
				)
				((User alterEgo?) edgeHit: 0)
			)
			(else
				(self setScript: enterRmScript)
			)
		)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
			)
			((not (cast contains: ego))
				NULL
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self setScript: leaveRmScript NULL nRoom)
			)
		)
	)
	
	(method (newRoom)
		(HandsOff)
		(super newRoom: &rest)
	)
)

(instance leaveRmScript of Script
	(properties
		name "lRS"
	)
	
	(method (dispose)
		(ego ignoreActors: 0)
		(super dispose:)
	)
	
	(method (changeState ns &tmp high wide)
		(switch (= state ns)
			(0
				(HandsOff)
				(ego ignoreActors: TRUE)
				(= wide 25)
				(switch register
					((client north?)
						(if (client walkOffTop?)
							(ego
								ignoreHorizon: TRUE
								setMotion: PolyPath (ego x?) -1 self
							)
						else
							(curRoom newRoom: register)
						)
					)
					((client south?)
						(= high (CelHigh (ego view?) (ego loop?) (ego cel?)))
						(if (IsObject (ego _head?))
							(= high (+ high (CelHigh ((ego _head?) view?) ((ego _head?) loop?) ((ego _head?) cel?)) 3))
						)
						(ego setMotion: PolyPath (ego x?) (+ southEdge high) self)
					)
					((client east?)
						(ego setMotion: PolyPath (+ eastEdge wide) (ego y?) self)
					)
					((client west?)
						(ego setMotion: PolyPath (- westEdge wide) (ego y?) self)
					)
				)
			)
			(1
				(ego hide:)
				(= cycles 1)
			)
			(2
				(curRoom setScript: 0 newRoom: register)
			)
		)
	)
)

(instance enterRmScript of Script
	(properties
		name "eRS"
	)
	
	(method (init)
		(ego ignoreActors: TRUE)
		(super init: &rest)
	)
	
	(method (changeState ns &tmp high wide)
		(switch (= state ns)
			(0
				(= cycles 0)
				(HandsOff)
				(= high (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(= wide 25)
				(switch prevRoomNum
					((client north?)
						(SeeIfOffX)
						(ego y: (+ (curRoom horizon?) (ego yStep?)))
						(= cycles 1)
					)
					((client south?)
						(SeeIfOffX)
						(ego
							y: (+ southEdge high)
							setMotion: noBlkMoveTo (ego x?) (- southEdge (* (ego yStep?) 2)) self
						)
					)
					((client east?)
						(SeeIfOffY)
						(ego
							x: (+ eastEdge (/ wide 2))
							setMotion: noBlkMoveTo (- eastEdge (* (ego xStep?) 2)) (ego y?) self
						)
					)
					((client west?)
						(SeeIfOffY)
						(ego
							x: (- 0 (/ wide 2))
							setMotion: noBlkMoveTo (+ 0 (* (ego xStep?) 2)) (ego y?) self
						)
					)
					(else  (= cycles 1))
				)
			)
			(1
				(HandsOn)
				(ego ignoreActors: FALSE)
				(client notify:)
				(self dispose:)
			)
		)
	)
)

(instance noBlkMoveTo of MoveTo
	(properties
		name "nBMT"
	)
	
	(method (doit)
		(super doit:)
		(if (client isBlocked:)
			(self moveDone:)
		)
	)
)
