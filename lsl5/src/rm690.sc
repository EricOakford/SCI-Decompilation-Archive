;;; Sierra Script 1.0 - (do not remove this comment)
(script# 690)
(include game.sh)
(use Main)
(use LLRoom)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm690 0
)

(local
	local0
)
(instance rm690 of LLRoom
	(properties
		picture 1
		style SCROLLUP
	)
	
	(method (init)
		(theIconBar disable:)
		(LoadMany PICTURE 690)
		(LoadMany VIEW 690)
		(LoadMany FONT giantFont2)
		(LoadMany SOUND 691)
		(super init:)
		(self setScript: sFBI)
	)
)

(instance sFBI of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(DoDisplay 3 global131 690 0)
				(= seconds 5)
			)
			(1
				(sonarScreen init:)
				(sweep init: setCycle: Forward)
				(blip init: setCycle: Forward)
				(curRoom drawPic: 690 12)
				(= seconds 5)
			)
			(2
				(theIconBar enable:)
				(TimePrint 690 1 #at -1 20 #title {Inspector Desmond} #dispose self)
			)
			(3 (= seconds 3))
			(4
				(if (Btst fBiazDrunk)
					(TimePrint 690 2 #at -1 20 #title {Agent Smith} #dispose self)
				else
					(TimePrint 690 3 #at -1 20 #title {Agent Smith} #dispose self)
				)
			)
			(5 (= seconds 3))
			(6
				(if modelessDialog (modelessDialog dispose:))
				(sweep dispose:)
				(blip dispose:)
				(curRoom drawPic: 1 IRISIN)
				(= seconds 3)
			)
			(7
				(if (Btst fBiazDrunk)
					(TimePrint 690 4)
				else
					(TimePrint 690 5)
				)
				(= seconds 3)
			)
			(8
				(curRoom newRoom: 200 IRISOUT)
				(self dispose:)
			)
		)
	)
)

(instance sonarScreen of View
	(properties
		x 163
		y 114
		view 690
		priority 1
		signal (| ignrAct fixPriOn)
	)
)

(instance sweep of Prop
	(properties
		x 161
		y 86
		view 690
		loop 2
		priority 12
		signal fixPriOn
		cycleSpeed 12
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(cel (= local0 0))
			((not local0) (sonarPing play:) (= local0 1))
		)
	)
)

(instance blip of Prop
	(properties
		x 170
		y 96
		view 690
		loop 1
		priority 12
		signal fixPriOn
		cycleSpeed 12
	)
)

(instance sonarPing of Sound
	(properties
		number 691
	)
)
