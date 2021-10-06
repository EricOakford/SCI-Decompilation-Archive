;;; Sierra Script 1.0 - (do not remove this comment)
(script# 23)
(include game.sh)
(use Main)
(use Intrface)
(use Actor)
(use System)

(public
	Whining 0
)

(local
	printObj
	markSpeaking
	scottSpeaking
)
(procedure (localproc_045e param1 param2)
	(param1 setLoop: param2)
)

(procedure (localproc_0469 param1 param2)
	(cls)
	(= printObj 0)
	(param1 setCel: 0 setLoop: param2)
)

(instance Whining of Script
	(properties)
	
	(method (init)
		(Load VIEW 212)
		(addToPics add: Mark)
		(addToPics doit:)
		(Scott init:)
		(MarkHead init:)
		(ScottHead init:)
		(super init: &rest)
	)
	
	(method (doit)
		(if markSpeaking (MarkHead setCel: (Random 0 4)))
		(if scottSpeaking (ScottHead setCel: (Random 0 5)))
		(super doit:)
	)
	
	(method (dispose)
		(Scott dispose: delete:)
		(MarkHead dispose: delete:)
		(ScottHead dispose: delete:)
	)
	
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(localproc_045e ScottHead 4)
				(= scottSpeaking 1)
				(= printObj
					(Print 23 0 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 10)
			)
			(2
				(localproc_0469 ScottHead 3)
				(= scottSpeaking 0)
				(= seconds 4)
			)
			(3
				(localproc_045e MarkHead 1)
				(= markSpeaking 1)
				(= printObj
					(Print 23 1 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 10)
			)
			(4
				(localproc_0469 MarkHead 1)
				(= markSpeaking 0)
				(= seconds 4)
			)
			(5
				(localproc_045e MarkHead 1)
				(= markSpeaking 1)
				(= printObj
					(Print 23 2 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 10)
			)
			(6
				(localproc_0469 MarkHead 1)
				(= markSpeaking 0)
				(= seconds 4)
			)
			(7
				(localproc_045e ScottHead 3)
				(= scottSpeaking 1)
				(= printObj
					(Print 23 3 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 10)
			)
			(8
				(localproc_0469 ScottHead 3)
				(= scottSpeaking 0)
				(= seconds 4)
			)
			(9
				(localproc_045e MarkHead 2)
				(= markSpeaking 1)
				(= printObj
					(Print 23 4 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 10)
			)
			(10
				(localproc_0469 MarkHead 1)
				(= markSpeaking 0)
				(self changeState: 0)
			)
		)
	)
)

(instance Mark of PicView
	(properties
		y 109
		x 34
		view 212
		priority 15
		signal ignrAct
	)
)

(instance Scott of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 212
			setLoop: 0
			setCel: 1
			posn: 70 101
			setPri: 14
			ignoreActors: 1
			setCycle: 0
		)
	)
)

(instance MarkHead of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 212
			setLoop: 1
			setCel: 0
			posn: 36 109
			setPri: 14
			ignoreActors: 1
			setCycle: 0
		)
	)
)

(instance ScottHead of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 212
			setLoop: 3
			setCel: 0
			posn: 72 102
			setPri: 14
			ignoreActors: 1
			setCycle: 0
		)
	)
)
