;;; Sierra Script 1.0 - (do not remove this comment)
(script# 420)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room420 0
)

(instance elevTop of View)

(instance elevBottom of View)

(instance cable1 of View)

(instance cable2 of View)

(instance cable3 of View)

(instance cable4 of View)

(instance Room420 of Room
	(properties
		picture 420
	)
	
	(method (init)
		(= global104 0)
		(super init:)
		(ego init:)
		(switch prevRoomNum
			(42
				(NormalEgo)
				(ego posn: 93 156)
				(elevTop
					view: 83
					ignoreActors:
					posn: 164 112
					setPri: 12
					loop: 1
					cel: 1
					init:
					stopUpd:
				)
				(elevBottom
					view: 83
					ignoreActors:
					posn: 164 112
					setPri: 7
					loop: 1
					cel: 0
					init:
					stopUpd:
				)
				(cable1
					view: 83
					loop: 1
					cel: 3
					ignoreActors:
					posn: 164 80
					setPri: 10
					init:
					stopUpd:
				)
				(cable2
					view: 83
					loop: 1
					cel: 3
					ignoreActors:
					posn: 164 42
					setPri: 10
					init:
					stopUpd:
				)
				(cable3
					view: 83
					loop: 1
					cel: 3
					ignoreActors:
					posn: 164 12
					setPri: 10
					init:
					stopUpd:
				)
				(cable4
					view: 83
					loop: 1
					cel: 3
					ignoreActors:
					posn: 164 0
					setPri: 10
					init:
					stopUpd:
				)
			)
			(421
				(ego view: 83 setLoop: 1 setCel: 2 posn:)
				(cable1
					view: 777
					loop: 1
					cel: 3
					ignoreActors:
					posn: 164 80
					setPri: 10
					init:
					stopUpd:
				)
				(cable2
					view: 777
					loop: 1
					cel: 3
					ignoreActors:
					posn: 164 42
					setPri: 10
					init:
					stopUpd:
				)
				(cable3
					view: 777
					loop: 1
					cel: 3
					ignoreActors:
					posn: 164 12
					setPri: 10
					init:
					stopUpd:
				)
				(cable4
					view: 777
					loop: 1
					cel: 3
					ignoreActors:
					posn: 164 0
					setPri: 10
					init:
					stopUpd:
				)
				(curRoom setScript: lowerElevator)
			)
		)
		(ego init:)
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if (& (ego onControl: origin) cBROWN)
			(curRoom newRoom: 42)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							(
								(or
									(Said '/area')
									(Said '/around')
									(Said '[<around][/noword]')
								)
								(Print 420 0)
							)
							((Said '/elevator')
								(Print 420 1)
							)
							((Said '/button,console,control')
								(Print 420 2)
							)
							((Said '<up')
								(Print 420 3)
							)
							((Said '/pit')
								(Print 420 4)
							)
							(else
								(Print 420 5)
								(event claimed: TRUE)
							)
						)
					)
					(
						(or
							(Said 'press/button')
							(Said 'use/elevator')
							(Said 'press<up/')
						)
						(if (ego inRect: 150 147 180 158)
							(curRoom setScript: raise)
						else
							(NotClose)
						)
					)
					((Said 'press<down')
						(Print 420 6)
					)
					((Said 'climb')
						(Print 420 7)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(if (!= n 421)
			(theMusic
				owner: -1
				number: 22
				priority: 1
				loop: -1
				play:
			)
		)
		(super newRoom: n)
	)
)

(instance raise of Script
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(cond 
			((< (Abs (- (ego y?) (cable1 y?))) 2)
				(cable1 dispose:)
			)
			((< (Abs (- (ego y?) (cable2 y?))) 2)
				(cable2 dispose:)
			)
			((< (Abs (- (ego y?) (cable3 y?))) 2)
				(cable3 dispose:)
			)
			((< (Abs (- (ego y?) (cable4 y?))) 2)
				(cable4 dispose:)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 164 153 self)
			)
			(1
				(ego view: 83 cel: 255 setLoop: 0 setCycle: EndLoop self)
			)
			(2
				(ego
					ignoreActors:
					illegalBits: 0
					setLoop: 1
					setCel: 2
					posn: 164 (elevTop y?)
					setPri: 11
					setMotion: MoveTo 164 -60 self
				)
				(elevTop dispose:)
				(elevBottom dispose:)
			)
			(3 (curRoom newRoom: 421))
		)
	)
)

(instance lowerElevator of Script
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(cond 
			((< (Abs (- (ego y?) (cable1 y?))) 2)
				(cable1
					view: 83
					loop: 1
					cel: 3
					forceUpd:
					stopUpd:
				)
			)
			((< (Abs (- (ego y?) (cable2 y?))) 2)
				(cable2
					view: 83
					loop: 1
					cel: 3
					forceUpd:
					stopUpd:
				)
			)
			((< (Abs (- (ego y?) (cable3 y?))) 2)
				(cable3
					view: 83
					loop: 1
					cel: 3
					forceUpd:
					stopUpd:
				)
			)
			((< (Abs (- (ego y?) (cable4 y?))) 2)
				(cable4
					view: 83
					loop: 1
					cel: 3
					forceUpd:
					stopUpd:
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 83
					ignoreActors:
					illegalBits: 0
					setLoop: 1
					setCel: 2
					posn: 164 -60
					setPri: 11
					setMotion: MoveTo 164 112 self
				)
			)
			(1
				(elevTop
					view: 83
					ignoreActors:
					posn: 164 112
					setPri: 12
					loop: 1
					cel: 1
					init:
					stopUpd:
				)
				(elevBottom
					view: 83
					ignoreActors:
					posn: 164 112
					setPri: 7
					loop: 1
					cel: 0
					init:
					stopUpd:
				)
				(NormalEgo)
				(ego
					view: 0
					setLoop: -1
					setCel: -1
					setCycle: Walk
					illegalBits: cWHITE
					posn: 164 153
				)
				(HandsOn)
			)
		)
	)
)
