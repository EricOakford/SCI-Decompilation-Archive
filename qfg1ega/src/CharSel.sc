;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHARSEL) ;202
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	selChar 0
)

(enum 1
	selectFighter
	selectMage
	selectThief
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
	highlightCued
	local15
	selCued
	firstTime =  TRUE
)
(procedure (ToCharAlloc)
	(HandsOff)
	(curRoom newRoom: CHARALLOC)
)

(procedure (HighlightCharacter)
	(switch whichChar
		(selectFighter
			(thePlaque setCel: 0 posn: 64 127 stopUpd:)
			(mageScript changeState: 0)
			(thiefScript changeState: 0)
			(fighterScript changeState: 1)
		)
		(selectMage
			(thePlaque setCel: 0 posn: 158 127 stopUpd:)
			(fighterScript changeState: 0)
			(thiefScript changeState: 0)
			(if selCued
				(theMage setLoop: 1 setCel: 3)
				(magicSwirl show:)
			else
				(mageScript changeState: 1)
			)
		)
		(selectThief
			(thePlaque setCel: 0 posn: 252 127 stopUpd:)
			(fighterScript changeState: 0)
			(mageScript changeState: 0)
			(thiefScript changeState: 1)
		)
	)
)

(procedure (SelectCharacter)
	(switch whichChar
		(selectFighter
			(= heroType FIGHTER)
			(ToCharAlloc)
		)
		(selectMage
			(= heroType MAGIC_USER)
			(ToCharAlloc)
		)
		(selectThief
			(= heroType THIEF)
			(ToCharAlloc)
		)
	)
)

(instance selChar of Room
	(properties
		picture 905
		style DISSOLVE
	)
	
	(method (init)
		(Load SOUND (SoundFX 73))
		(User canInput: FALSE)
		((= thePlaque (View new:))
			view: vCharSelect
			setLoop: 0
			setCel: 0
			posn: 0 1000
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
		)
		((= theFighter (Prop new:))
			view: vEgoCharSelect
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
			setScript: fighterScript
		)
		((= magicSwirl (Prop new:))
			view: vEgoCharSelect
			setLoop: 3
			setCel: 0
			posn: 158 84
			setPri: 6
			init:
			ignoreActors:
		)
		((= theMage (Prop new:))
			view: vEgoCharSelect
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
			setScript: mageScript
		)
		((= theThief (Prop new:))
			view: vEgoCharSelect
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
			setScript: thiefScript
		)
		((= chooseText (View new:))
			view: vCharSelect
			setLoop: 1
			setCel: 0
			posn: 83 25
			init:
			ignoreActors:
			stopUpd:
		)
		((= yourText (View new:))
			view: vCharSelect
			setLoop: 1
			setCel: 1
			posn: 146 27
			init:
			ignoreActors:
			stopUpd:
		)
		((= characterText (View new:))
			view: vCharSelect
			setLoop: 1
			setCel: 2
			posn: 220 27
			init:
			ignoreActors:
			stopUpd:
		)
		((= fighterText (View new:))
			view: vCharSelect
			setLoop: 1
			setCel: 3
			posn: 65 155
			init:
			ignoreActors:
			stopUpd:
		)
		((= mageText (View new:))
			view: vCharSelect
			setLoop: 1
			setCel: 4
			posn: 159 147
			init:
			ignoreActors:
			stopUpd:
		)
		((= thiefText (View new:))
			view: vCharSelect
			setLoop: 1
			setCel: 6
			posn: 161 163
			init:
			ignoreActors:
			stopUpd:
		)
		((= userText (View new:))
			view: vCharSelect
			setLoop: 1
			setCel: 5
			posn: 252 153
			init:
			ignoreActors:
			stopUpd:
		)
		(self setScript: selScript)
		(super init:)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(directionHandler add: self)
		(if
			(or
				(== (cSound state?) 0)
				(!= (cSound number?) (SoundFX 61))
			)
			(cSound number: (SoundFX 73) loop: -1 play:)
		)
		(cSound prevSignal: 0)
		(Joystick JoyRepeat 30)
	)
	
	(method (doit)
		(cond 
			(
				(and
					firstTime
					(or
						(== (cSound signal?) 2)
						(== (cSound prevSignal?) 2)
					)
				)
				(= firstTime FALSE)
				(cSound stop:)
				(cSound number: (SoundFX 73) loop: -1 play:)
			)
			((and local15 (== (cSound prevSignal?) 3))
				(cSound prevSignal: 0)
				(if (== whichChar selectThief)
					(= whichChar selectFighter)
				else
					(++ whichChar)
				)
				(= highlightCued FALSE)
				(HighlightCharacter)
			)
			(selCued
				(= selCued FALSE)
				(SelectCharacter)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Joystick JoyRepeat 0)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp thisControl evtX evtY [str 60])
		(if highlightCued
			(if (== (event type?) keyDown)
				(= local15 0)
				(switch (event message?)
					(TAB
						(event type: direction)
						(event message: dirE)
					)
					(SHIFTTAB
						(event type: direction)
						(event message: dirW)
					)
					(ENTER
						(event claimed: TRUE)
						(= selCued TRUE)
					)
				)
			)
			(switch (event type?)
				(mouseDown
					(= evtX (event x?))
					(= evtY (event y?))
					(= thisControl (OnControl CMAP evtX evtY))
					(= local15 0)
					(event claimed: TRUE)
					(cond 
						((& thisControl cYELLOW)
							(= whichChar selectFighter)
							(= selCued TRUE)
						)
						((& thisControl cLMAGENTA)
							(= whichChar selectMage)
							(= selCued TRUE)
						)
						((& thisControl cLRED)
							(= whichChar selectThief)
							(= selCued TRUE)
						)
						(else
							(event claimed: FALSE)
						)
					)
					(event claimed: TRUE)
					(= highlightCued FALSE)
					(HighlightCharacter)
				)
				(direction
					(switch (event message?)
						(dirW
							(if (== whichChar selectFighter)
								(= whichChar selectThief)
							else
								(-- whichChar)
							)
						)
						(dirE
							(if (== whichChar selectThief)
								(= whichChar selectFighter)
							else
								(++ whichChar)
							)
						)
					)
					(event claimed: TRUE)
					(= local15 0)
					(= highlightCued FALSE)
					(HighlightCharacter)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance fighterScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (= cycles 0))
				(theFighter setLoop: 4 setCel: 0 posn: 64 117)
			)
			(1
				(= seconds (= cycles 0))
				(= whichChar selectFighter)
				(theFighter setLoop: 0 setCel: 1)
				(= highlightCued TRUE)
				(= cycles 5)
			)
			(2
				(theFighter setCel: 0)
				(= cycles 5)
			)
			(3
				(fighterScript changeState: 1)
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
				(magicSwirl stopUpd: hide:)
			)
			(1
				(= seconds (= cycles 0))
				(= whichChar selectMage)
				(theMage setLoop: 1 setCel: 0)
				(= highlightCued TRUE)
				(= cycles 3)
			)
			(2
				(theMage setCel: -1 setCycle: EndLoop self)
			)
			(3
				(magicSwirl show: setCycle: Forward startUpd:)
				(= seconds 3)
			)
			(4
				(self changeState: 3)
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
				(= whichChar selectThief)
				(theThief setLoop: 2 setCel: 0)
				(= highlightCued TRUE)
				(= seconds 2)
			)
			(2
				(theThief setCel: -1 setCycle: EndLoop)
				(= seconds 2)
			)
			(3
				(theThief setCycle: BegLoop self)
			)
			(4
				(thiefScript changeState: 1)
			)
		)
	)
)

(instance selScript of Script
	(method (doit)
		(if
			(and
				(== state 3)
				(not local15)
				(== (cSound prevSignal?) 3)
			)
			(cSound prevSignal: 0)
			(= highlightCued TRUE)
			(= local15 1)
			(fighterScript changeState: 1)
			(self dispose:)
		else
			(super doit:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(++ charCycles)
				(chooseText hide:)
				(yourText hide:)
				(characterText hide:)
				(= cycles 3)
			)
			(2
				(chooseText show:)
				(yourText show:)
				(characterText show:)
				(= cycles 3)
			)
			(3
				(cSound prevSignal: 0)
				(if (< charCycles 2)
					(self changeState: 1)
				)
			)
		)
	)
)
