;;; Sierra Script 1.0 - (do not remove this comment)
(script# 24)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Actor)
(use System)

(public
	FixLSpeed 0
)

(local
	printObj
	markSpeaking
	scottSpeaking
)
(procedure (localproc_082c param1 param2)
	(param1 setLoop: param2)
)

(procedure (localproc_0837 param1 param2)
	(cls)
	(= printObj 0)
	(param1 setCel: 0 setLoop: param2)
)

(instance FixLSpeed of Script
	(properties)
	
	(method (init)
		(Load VIEW 212)
		(Load VIEW 65)
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
		(lp1 dispose: delete:)
		(lp2 dispose: delete:)
		(lp3 dispose: delete:)
		(lp4 dispose: delete:)
	)
	
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(HandsOff)
				(= saveDisabled 1)
				(localproc_082c MarkHead 1)
				(= markSpeaking 1)
				(= printObj
					(Print 24 0 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 10)
			)
			(1
				(localproc_0837 MarkHead 1)
				(= markSpeaking 0)
				(= seconds 4)
			)
			(2 (Print 24 1) (= cycles 2))
			(3
				(localproc_082c MarkHead 1)
				(= markSpeaking 1)
				(= printObj
					(Print 24 2 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 8)
			)
			(4
				(localproc_0837 MarkHead 1)
				(= markSpeaking 0)
				(= seconds 2)
			)
			(5
				(Scott setCel: 3)
				(ScottHead dispose:)
				(= printObj
					(Print 24 3 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 4)
			)
			(6
				(cls)
				(= printObj
					(Print 24 4 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 8)
			)
			(7
				(cls)
				(= printObj
					(Print 24 5 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 8)
			)
			(8
				(cls)
				(= printObj
					(Print 24 6 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 6)
			)
			(9
				(cls)
				(Scott setCel: 1)
				(ScottHead init:)
				(Print 24 7)
				(lp1 init:)
				(lp2 init:)
				(lp3 init:)
				(lp4 init:)
				(theMusic number: 69 loop: -1 play:)
				(= seconds 2)
			)
			(10
				(localproc_082c MarkHead 1)
				(= markSpeaking 1)
				(= printObj
					(Print 24 8 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 2)
			)
			(11
				(localproc_0837 MarkHead 1)
				(= markSpeaking 0)
				(= seconds 1)
			)
			(12
				(localproc_082c ScottHead 3)
				(= scottSpeaking 1)
				(= printObj
					(Print 24 9 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 2)
			)
			(13
				(localproc_0837 ScottHead 3)
				(= scottSpeaking 0)
				(= seconds 1)
			)
			(14
				(localproc_082c MarkHead 1)
				(= markSpeaking 1)
				(= printObj
					(Print 24 10 #at 10 25 #width 100 #font 600 #dispose)
				)
				(= seconds 2)
			)
			(15
				(localproc_0837 MarkHead 1)
				(= markSpeaking 0)
				(= seconds 5)
			)
			(16
				(Print 24 11)
				(lp1 setCycle: 0)
				(lp2 setCycle: 0)
				(lp3 setCycle: 0)
				(lp4 setCycle: 0)
				(= global258 1)
				(theMusic fade:)
				(curRoom newRoom: 99)
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

(instance lp1 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 65
			setLoop: 0
			ignoreActors: 1
			posn: 160 83
			setPri: 6
			setCycle: Forward
		)
	)
)

(instance lp2 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 65
			setLoop: 2
			ignoreActors: 1
			posn: 160 83
			setPri: 6
			setCycle: Forward
		)
	)
)

(instance lp3 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 65
			setLoop: 1
			ignoreActors: 1
			posn: 160 83
			setPri: 6
			setCycle: Forward
		)
	)
)

(instance lp4 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 65
			setLoop: 3
			ignoreActors: 1
			posn: 160 83
			setPri: 6
			setCycle: Forward
		)
	)
)
