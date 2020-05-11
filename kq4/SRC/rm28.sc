;;; Sierra Script 1.0 - (do not remove this comment)
(script# 28)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room28 0
)

(local
	gEgoOnControl
	local1
	local2
	theGEgoOnControl
	mineDoor
)
(instance fallSound of Sound
	(properties
		number 51
	)
)

(instance Room28 of Room
	(properties
		picture 28
	)
	
	(method (init)
		(= north 22)
		(= south 4)
		(= east 29)
		(= west 27)
		(= horizon 126)
		(= isIndoors FALSE)
		(HandsOn)
		(ego edgeHit: 0 illegalBits: -32768)
		(super init:)
		(if isNightTime (curRoom overlay: 128))
		(self setRegions: FOREST)
		(Load VIEW 17)
		(Load VIEW 21)
		(Load SOUND 51)
		(= mineDoor (View new:))
		(if isNightTime
			(mineDoor
				view: 600
				loop: 0
				cel: 0
				setPri: 9
				posn: 289 137
				ignoreActors:
				init:
				stopUpd:
			)
			(ego observeControl: 16384)
		else
			(mineDoor
				view: 600
				loop: 1
				cel: 0
				ignoreActors: 1
				setPri: 9
				posn: 284 131
				init:
				stopUpd:
			)
		)
		(switch prevRoomNum
			(22 (ego posn: 82 128))
			(27
				(if (<= (ego y?) horizon)
					(ego posn: 2 (+ horizon 2))
				else
					(ego posn: 2 (ego y?))
				)
			)
			(29
				(if (<= (ego y?) 156)
					(ego x: 318 y: 149)
				else
					(ego posn: 317 187)
				)
			)
			(4 (ego posn: 158 187))
			(55 (ego x: 290 y: 134))
			(0 (ego x: 88 y: 160))
		)
		(ego view: 2 xStep: 3 yStep: 2 init:)
		(ego y: (| (ego y?) $0001))
	)
	
	(method (doit)
		(super doit:)
		(if (& (= gEgoOnControl (ego onControl:)) $0040)
			(curRoom newRoom: 55)
		)
		(if
			(and
				(!= gEgoOnControl theGEgoOnControl)
				(== (curRoom script?) 0)
				(== curRoomNum newRoomNum)
			)
			(= theGEgoOnControl gEgoOnControl)
			(if (& gEgoOnControl $0004)
				(self setScript: fallSouth)
				(curRoom east: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(if (not isNightTime)
					(if (Said 'look>')
						(cond 
							((Said '/door') (Print 28 0))
							((Said '<in[/mine]')
								(if (ego inRect: 269 135 295 142)
									(Print 28 1)
								else
									(Print 800 1)
								)
							)
							((Said '/mine,hill') (Print 28 2))
							((Said '[<around][/room]') (Print 28 3))
						)
					)
					(if (Said '/door>')
						(cond 
							((Said 'close,close') (Print 28 4))
							((Said 'open,bang') (Print 28 5))
						)
					)
				else
					(if (Said '/door,padlock,latch>')
						(cond 
							((Said 'break/door') (Print 28 6))
							((Said 'bang/door')
								(if (ego inRect: 276 131 298 143)
									(Print 28 7)
								else
									(Print 800 1)
								)
							)
							((Said 'open/door') (Print 28 8))
							((Said 'unlatch,detach,get') (Print 28 9))
						)
					)
					(if (Said 'look>')
						(cond 
							((Said '/door') (Print 28 10))
							((Said '/mine,hill') (Print 28 11))
							((Said '[<around][/room]') (Print 28 3))
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(super newRoom: newRoomNumber)
	)
)

(instance fallSouth of Script
	(properties)
	
	(method (init)
		(ego viewer: 0)
		(super init:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(fallSound play:)
				(if (< (ego x?) 265) (= local2 20) else (= local2 30))
				(if (> (+ (ego y?) local2) 188)
					(= local1 188)
				else
					(= local1 (+ (ego y?) local2))
				)
				(ego
					view: 17
					setStep: 6 6
					illegalBits: 0
					loop: (& (ego loop?) $0001)
					setCel: 0
					setCycle: EndLoop
					setMotion: MoveTo (ego x?) local1 self
				)
			)
			(1
				(ego
					xStep: 3
					yStep: 2
					view: 21
					loop: 2
					setCycle: CycleTo 4 1 self
				)
			)
			(2
				(ego view: 21 loop: 2 setCycle: BegLoop self)
			)
			(3
				(curRoom setScript: 0)
				(ego illegalBits: -32768 setCycle: Walk view: 2)
				(if isNightTime (ego observeControl: 16384))
				(curRoom east: 29)
				(HandsOn)
			)
		)
	)
)
