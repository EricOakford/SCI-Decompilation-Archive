;;; Sierra Script 1.0 - (do not remove this comment)
(script# 53)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room53 0
)

(instance hissing of Sound
	(properties)
)

(instance aSnake of Actor
	(properties)
)

(instance tongue of Prop
	(properties)
)

(instance snakeSound of Sound
	(properties)
)

(instance Room53 of Room
	(properties
		picture 53
	)
	
	(method (init)
		(= north prevRoomNum)
		(= global109 0)
		(Load SOUND 36)
		(Load SOUND 37)
		(Load SOUND 88)
		(Load SOUND 89)
		(self setRegions: DUNE)
		(super init:)
		(NormalEgo)
		(theMusic stop:)
		(User canControl: FALSE canInput: TRUE)
		(ego posn: 155 95 view: 0 init: setMotion: MoveTo 155 105)
		(aSnake
			view: 77
			posn: -40 150
			setPri: 14
			setLoop: 0
			cel: 0
			setStep: 4 3
			ignoreActors: 1
			ignoreHorizon:
			illegalBits: 0
			init:
		)
		(aSnake setScript: snakeActions)
		(curRoom setScript: Actions)
	)
	
	(method (dispose)
		(DisposeScript JUMP)
		(super dispose:)
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
									(Said '[<around][/!*]')
								)
								(Print 53 0)
							)
							((Said '/cruncher') (Print 53 1))
							((Said '/snake') (Print 53 2))
						)
					)
					((Said 'kiss/cruncher') (Print 53 3))
					((Said 'attack/cruncher') (Print 53 4))
					((Said 'jog') (Print 53 5))
					((Said 'feed/cruncher') (Print 53 6))
					((Said 'converse/cruncher') (Print 53 7))
					((Said 'bridle/cruncher') (Print 53 8) (theGame changeScore: -5))
					((Said 'charm/cruncher') (Print 53 9))
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(theMusic number: 22 priority: 0 loop: -1 play:)
		(HandsOn)
		(= global104 0)
		(super newRoom: newRoomNumber)
	)
)

(instance Actions of Script
	(properties)
	
	(method (changeState)
	)
)

(instance snakeActions of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if (== state 4)
			(cond 
				((or (!= (ego x?) 155) (> (ego y?) 105))
					(User canControl: FALSE canInput: TRUE)
					(= seconds 0)
					(self changeState: 5)
				)
				(
				(and (<= (ego y?) 95) (== curRoomNum newRoomNum)) (User canControl: FALSE canInput: TRUE) (self changeState: 10))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aSnake setMotion: MoveTo -22 94 self)
			)
			(1
				(aSnake setMotion: MoveTo -43 55 self)
			)
			(2
				(aSnake setMotion: MoveTo 0 62 self)
			)
			(3
				(if (IsObject (ego mover?))
					(-- state)
					(= cycles 2)
				else
					(self cue:)
				)
			)
			(4
				(tongue
					view: 77
					loop: 2
					posn: 133 83
					setPri: 13
					setCycle: Forward
					setScript: hiss
					init:
				)
				(HandsOn)
				(= seconds 12)
			)
			(5
				(HandsOff)
				(hissing stop:)
				(hiss changeState: 3)
				(tongue setScript: 0 dispose:)
				(aSnake setCycle: EndLoop self)
				(snakeSound priority: 19 number: 89 play:)
			)
			(6
				(aSnake cel: 0)
				(curRoom horizon: 0)
				(ego
					view: 77
					setLoop: 3
					posn: 141 89
					ignoreActors:
					setCycle: Forward
					setStep: 1 1
					moveSpeed: 2
					setMotion: MoveTo 126 87
				)
				(= seconds 7)
			)
			(7
				(ego setLoop: 4 setCel: 0 posn: 137 88)
				(snakeSound priority: 19 number: 88 play:)
				(aSnake cel: 1)
				(= seconds 1)
			)
			(8
				(ego hide:)
				(aSnake cel: 0)
				(= seconds 3)
			)
			(9 (EgoDead 901 0 13 19))
			(10
				(tongue dispose:)
				(ego setMotion: MoveTo (ego x?) 70 self)
			)
			(11
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance hiss of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hissing number: 37 priority: 10 loop: 2 play: self)
			)
			(1 (= state -1) (= seconds 4))
		)
	)
)
