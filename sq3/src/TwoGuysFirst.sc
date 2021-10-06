;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh)
(use Main)
(use Intrface)
(use Actor)
(use System)

(public
	TwoGuysFirst 0
)

(local
	printObj
	markSpeaking
	scottSpeaking
)
(procedure (localproc_03be param1 param2)
	(param1 setLoop: param2)
)

(procedure (localproc_03c9 param1 param2)
	(cls)
	(= printObj 0)
	(param1 setCel: 0 setLoop: param2)
)

(instance TwoGuysFirst of Script
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
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(localproc_03be MarkHead 1)
				(= markSpeaking 1)
				(= printObj
					(Print 22 0 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 12)
			)
			(2
				(= markSpeaking 0)
				(localproc_03c9 MarkHead 1)
				(= seconds 4)
			)
			(3
				(localproc_03be ScottHead 3)
				(= scottSpeaking 1)
				(= printObj
					(Print 22 1 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 12)
			)
			(4
				(= scottSpeaking 0)
				(localproc_03c9 ScottHead 3)
				(= seconds 6)
			)
			(5
				(localproc_03be MarkHead 1)
				(= markSpeaking 1)
				(= printObj
					(Print 22 2 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 12)
			)
			(6
				(= markSpeaking 0)
				(localproc_03c9 MarkHead 1)
				(if (> global175 15) (= global175 15))
				(curRoom setScript: 0)
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
