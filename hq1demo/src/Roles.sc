;;; Sierra Script 1.0 - (do not remove this comment)
(script# ROLES)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Save)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	selChar 0
)

(local
	theFighter
	theMage
	magicSwirl
	theThief
	chooseText
	yourText
	characterText
	fighterText
	mageText
	thiefText
	userText
	selectedClass
	numAnimationCycles
	thePlaque
)

(enum 1	;character classes
	FIGHTER
	MAGE
	THIEF
)
	
(procedure (HighlightCharacter)
	(switch selectedClass
		(FIGHTER
			(thePlaque setCel: 0 posn: 64 127 stopUpd:)
			(mageScript changeState: 0)
			(thiefScript changeState: 0)
			(fighterScript changeState: 1)
		)
		(MAGE
			(thePlaque setCel: 0 posn: 158 127 stopUpd:)
			(fighterScript changeState: 0)
			(thiefScript changeState: 0)
			(mageScript changeState: 1)
		)
		(THIEF
			(thePlaque setCel: 0 posn: 252 127 stopUpd:)
			(fighterScript changeState: 0)
			(mageScript changeState: 0)
			(thiefScript changeState: 1)
		)
	)
)

(instance selChar of Room
	(properties
		picture 905
		style IRISIN
	)
	
	(method (init)
		(HandsOff)
		(LoadMany SOUND 73 173)
		(super init:)
		((= thePlaque (View new:))
			view: 506
			setLoop: 0
			setCel: 0
			posn: 0 1000
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
		)
		((= theFighter (Prop new:))
			view: 505
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
			setScript: fighterScript
		)
		((= magicSwirl (Prop new:))
			view: 505
			setLoop: 3
			setCel: 0
			posn: 158 84
			setPri: 6
			init:
			hide:
			ignoreActors:
		)
		((= theMage (Prop new:))
			view: 505
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
			setScript: mageScript
		)
		((= theThief (Prop new:))
			view: 505
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
			setScript: thiefScript
		)
		((= chooseText (View new:))
			view: 506
			setLoop: 1
			setCel: 0
			posn: 83 25
			init:
			ignoreActors:
			stopUpd:
		)
		((= yourText (View new:))
			view: 506
			setLoop: 1
			setCel: 1
			posn: 146 27
			init:
			ignoreActors:
			stopUpd:
		)
		((= characterText (View new:))
			view: 506
			setLoop: 1
			setCel: 2
			posn: 220 27
			init:
			ignoreActors:
			stopUpd:
		)
		((= fighterText (View new:))
			view: 506
			setLoop: 1
			setCel: 3
			posn: 65 155
			init:
			ignoreActors:
			stopUpd:
		)
		((= mageText (View new:))
			view: 506
			setLoop: 1
			setCel: 4
			posn: 159 147
			init:
			ignoreActors:
			stopUpd:
		)
		((= thiefText (View new:))
			view: 506
			setLoop: 1
			setCel: 6
			posn: 161 163
			init:
			ignoreActors:
			stopUpd:
		)
		((= userText (View new:))
			view: 506
			setLoop: 1
			setCel: 5
			posn: 252 153
			init:
			ignoreActors:
			stopUpd:
		)
		(self setScript: selScript)
		(music
			number: (if (== numVoices 1) 173 else 73)
			loop: -1
			play:
		)
		(HandsOff)
		(Print 2 0
			#width 290
			#at -1 180
			#mode teJustCenter
			#dispose
			#window aWin
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance fighterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(++ numAnimationCycles)
				(= seconds (= cycles 0))
				(theFighter setLoop: 4 setCel: 0 posn: 64 117)
			)
			(1
				(= seconds (= cycles 0))
				(= selectedClass FIGHTER)
				(theFighter setLoop: 0 setCel: 0)
				(= cycles 4)
			)
			(2
				(theFighter setCel: 1)
				(= cycles 4)
			)
			(3
				(theFighter setCel: 0)
				(= cycles 4)
			)
			(4
				(theFighter setCel: 1)
				(= cycles 4)
			)
			(5
				(theFighter setCel: 0)
				(= cycles 4)
			)
			(6
				(theFighter setCel: 1)
				(= cycles 4)
			)
			(7
				(if (< numAnimationCycles 2)
					(++ selectedClass)
					(HighlightCharacter)
				else
					(music fade:)
					(curRoom newRoom: TOWN)
				)
			)
		)
	)
)

(instance mageScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (= cycles 0))
				(theMage setLoop: 4 setCel: 1 posn: 158 117)
			)
			(1
				(= seconds (= cycles 0))
				(= selectedClass MAGE)
				(theMage setLoop: 1 setCel: 0)
				(= cycles 3)
			)
			(2
				(theMage setCel: -1 setCycle: EndLoop self)
			)
			(3
				(magicSwirl show: setCycle: Forward startUpd:)
				(= seconds 4)
			)
			(4
				(magicSwirl hide: stopUpd:)
				(theMage setCycle: BegLoop self)
			)
			(5
				(++ selectedClass)
				(HighlightCharacter)
			)
		)
	)
)

(instance thiefScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (= cycles 0))
				(theThief setLoop: 4 setCel: 2 posn: 252 117)
			)
			(1
				(= seconds (= cycles 0))
				(theThief setLoop: 2 setCel: 0)
				(= cycles 10)
			)
			(2
				(theThief setCel: -1 setCycle: EndLoop)
				(= seconds 4)
			)
			(3
				(theThief setCycle: BegLoop self)
			)
			(4
				(if modelessDialog (modelessDialog dispose:))
				(= selectedClass FIGHTER)
				(HighlightCharacter)
			)
		)
	)
)

(instance selScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(++ numAnimationCycles)
				(chooseText hide:)
				(yourText hide:)
				(characterText hide:)
				(= cycles 8)
			)
			(2
				(chooseText show:)
				(yourText show:)
				(characterText show:)
				(= cycles 8)
			)
			(3
				(if (< numAnimationCycles 3)
					(self changeState: 1)
				else
					(= selectedClass FIGHTER)
					(HighlightCharacter)
					(= numAnimationCycles 0)
					(self dispose:)
				)
			)
		)
	)
)

(instance aWin of SysWindow
	(properties
		color 4
		back 14
	)
)
