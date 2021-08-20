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
	whichChar
	charCycles
	thePlaque
)
	
(procedure (HighlightCharacter)
	(switch whichChar
		(FIGHTER
			(thePlaque setCel: 0 posn: 64 127 stopUpd:)
			(mageScript changeState: 0)
			(thiefScript changeState: 0)
			(fighterScript changeState: 1)
		)
		(MAGIC_USER
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
		picture pCharSel
		style IRISIN
	)
	
	(method (init)
		(HandsOff)
		(LoadMany SOUND sBrigand sBrigandIBM)
		(super init:)
		((= thePlaque (View new:))
			view: vCharSel
			setLoop: 0
			setCel: 0
			posn: 0 1000
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
		)
		((= theFighter (Prop new:))
			view: vSelEgo
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
			setScript: fighterScript
		)
		((= magicSwirl (Prop new:))
			view: vSelEgo
			setLoop: 3
			setCel: 0
			posn: 158 84
			setPri: 6
			init:
			hide:
			ignoreActors:
		)
		((= theMage (Prop new:))
			view: vSelEgo
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
			setScript: mageScript
		)
		((= theThief (Prop new:))
			view: vSelEgo
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
			setScript: thiefScript
		)
		((= chooseText (View new:))
			view: vCharSel
			setLoop: 1
			setCel: 0
			posn: 83 25
			init:
			ignoreActors:
			stopUpd:
		)
		((= yourText (View new:))
			view: vCharSel
			setLoop: 1
			setCel: 1
			posn: 146 27
			init:
			ignoreActors:
			stopUpd:
		)
		((= characterText (View new:))
			view: vCharSel
			setLoop: 1
			setCel: 2
			posn: 220 27
			init:
			ignoreActors:
			stopUpd:
		)
		((= fighterText (View new:))
			view: vCharSel
			setLoop: 1
			setCel: 3
			posn: 65 155
			init:
			ignoreActors:
			stopUpd:
		)
		((= mageText (View new:))
			view: vCharSel
			setLoop: 1
			setCel: 4
			posn: 159 147
			init:
			ignoreActors:
			stopUpd:
		)
		((= thiefText (View new:))
			view: vCharSel
			setLoop: 1
			setCel: 6
			posn: 161 163
			init:
			ignoreActors:
			stopUpd:
		)
		((= userText (View new:))
			view: vCharSel
			setLoop: 1
			setCel: 5
			posn: 252 153
			init:
			ignoreActors:
			stopUpd:
		)
		(self setScript: selScript)
		(globalMusic
			number: (if (== numVoices 1) sBrigandIBM else sBrigand)
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

	(method (changeState newState)
		(switch (= state newState)
			(0
				(++ charCycles)
				(= seconds (= cycles 0))
				(theFighter setLoop: 4 setCel: 0 posn: 64 117)
			)
			(1
				(= seconds (= cycles 0))
				(= whichChar FIGHTER)
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
				(if (< charCycles 2)
					(++ whichChar)
					(HighlightCharacter)
				else
					(globalMusic fade:)
					(curRoom newRoom: TOWN)
				)
			)
		)
	)
)

(instance mageScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (= cycles 0))
				(theMage setLoop: 4 setCel: 1 posn: 158 117)
			)
			(1
				(= seconds (= cycles 0))
				(= whichChar MAGIC_USER)
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
				(++ whichChar)
				(HighlightCharacter)
			)
		)
	)
)

(instance thiefScript of Script
	
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
				(= whichChar FIGHTER)
				(HighlightCharacter)
			)
		)
	)
)

(instance selScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(++ charCycles)
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
				(if (< charCycles 3)
					(self changeState: 1)
				else
					(= whichChar FIGHTER)
					(HighlightCharacter)
					(= charCycles 0)
					(self dispose:)
				)
			)
		)
	)
)

(instance aWin of SysWindow
	(properties
		color vRED
		back vYELLOW
	)
)
