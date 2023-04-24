;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHARSEL)
(include game.sh)
(use Main)
(use PMouse)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	selChar 0
)

(local
	fightSoundCued
	thiefSoundCued
	titlePlateOnScreen
	titleCued
	titleX = [18 202 201 18 212 201]
	titleY = [146 156 146 153 160 153]
)
(procedure (SelectedCharacter charType &tmp evt)
	(if (fightChar cel?)
		(if (< howFast fast)
			(fightChar setCel: 0 cue:)
		else
			(fightChar setCycle: BegLoop fightChar)
		)
	)
	(if (mageChar cel?)
		(if (< howFast fast)
			(mageChar
				setCel: 0
				cue:
			)
		else
			(mageArm
				cel: 0	;5
				stopUpd:
			)
			(mageChar setCycle: BegLoop mageChar)
		)
	)
	(if (thiefChar cel?)
		(if (< howFast fast)
			(thiefChar setCel: 0 cue:)
		else
			(thiefChar setCycle: BegLoop thiefChar)
		)
	)
	(theTitle
		setLoop: 1
		cel: charType
		x: [titleX charType]
		y: [titleY charType]
	)
	(if
		(or
			(theTitle onMe: (= evt (Event new:)))
			(not titlePlateOnScreen)
		)
		(Bclr fHideCursor)
		(theGame setCursor: ARROW_CURSOR TRUE
			(switch charType
				(FIGHTER 50)
				(MAGIC_USER 140)
				(else  235)
			)
			150
		)
	)
	(evt dispose:)
)

(instance selChar of Room
	(properties
		picture pCharSel
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(theIconBar disable:)
		(= useSortedFeatures FALSE)
		(= pMouse 0)
		(mouseDownHandler
			add: theTitle fightChar mageChar mageArm thiefChar self
		)
		(keyDownHandler
			add: self theTitle fightChar mageChar mageArm thiefChar
		)
		(directionHandler add: self)
		(fightChar init: stopUpd:)
		(mageChar init: stopUpd:)
		(thiefChar init: stopUpd:)
		(theTitle
			loop: (if (== prevRoomNum CHALLOC) 0 else 3)
			init:
		)
		(roundRobin start: (if (== prevRoomNum CHALLOC) 2 else 0))
		(self setScript: roundRobin)
		(if (and (not (theMusic handle?)) (!= (theMusic number?) sCharSel))
			(theMusic
				loop: -1
				number: sCharSel
				play:
			)
		)
	)
	
	(method (dispose)
		(= useSortedFeatures TRUE)
		(= pMouse PseudoMouse)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (or (event claimed?) titleCued)
			(event claimed: TRUE)
			(return)
		)
		(if (== (event type?) keyDown)
			(switch (event message?)
				(TAB
					(event type: direction)
					(event message: dirE)
				)
				(SHIFTTAB
					(event type: direction)
					(event message: dirW)
				)
			)
		)
		(if (& (event type?) direction)
			(switch (event message?)
				(dirW
					(event claimed: TRUE)
					(script state:
						(switch (- (theTitle cel?) 1)
							(0 1)
							(1 3)
							(else  6)
						)
						cue:
					)
				)
				(dirE
					(event claimed: TRUE)
					(script
						state:
						(switch (+ (theTitle cel?) 1)
							(1 3)
							(2 6)
							(else  1)
						)
						cue:
					)
				)
			)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)

(instance fightChar of Prop
	(properties
		x 68
		y 129
		view vSelFighter
		signal skipCheck
	)
	
	(method (init)
		(super init:)
		(if (< howFast fast)
			(self view: vSelFighterSlow)
		else
			(self view: vSelFighter)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(or
					(and
						(== (event type?) mouseDown)
						(self onMe: (event x?) (event y?))
					)
					(and
						(== (event type?) keyDown)
						(OneOf (event message?) ESC ENTER)
					)
				)
				(not titleCued)
				(!= (roundRobin state?) 2)
			)
			(event claimed: TRUE)
			(if (== (roundRobin register?) 1)
				(theTitle cue:)
			else
				(roundRobin changeState: 2)
			)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb)
		(return FALSE)
	)
	
	(method (cue)
		(super cue:)
		(self stopUpd:)
	)
)

(instance mageChar of Prop
	(properties
		x 158
		y 129
		view vSelMagicUser
		signal skipCheck
	)
	
	(method (init)
		(super init:)
		(if (< howFast fast)
			(self view: vSelMagicUserSlow)
		else
			(self view: vSelMagicUser)
		)
	)
	
	(method (handleEvent event &tmp roundRobinState)
		(= roundRobinState (roundRobin state?))
		(if
			(and
				(or
					(and
						(== (event type?) mouseDown)
						(self onMe: (event x?) (event y?))
					)
					(and
						(== (event type?) keyDown)
						(OneOf (event message?) ESC ENTER)
					)
				)
				(not titleCued)
				(!= roundRobinState 4)
				(!= roundRobinState 5)
			)
			(event claimed: TRUE)
			(if (== (roundRobin register?) 2)
				(theTitle cue:)
			else
				(roundRobin changeState: 4)
			)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb)
		(return FALSE)
	)
	
	(method (cue)
		(super cue:)
		(self stopUpd:)
	)
)

(instance mageArm of Prop
	(properties
		x 162
		y 73
		view vSelMagicUser
		loop 1
		priority 15
		signal (| skipCheck fixPriOn)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)

(instance thiefChar of Prop
	(properties
		x 248
		y 130
		view vSelThief
		signal skipCheck
	)
	
	(method (init)
		(super init:)
		(if (< howFast fast)
			(self view: vSelThiefSlow)
		else
			(self view: vSelThief)
		)
	)
	
	(method (handleEvent event &tmp roundRobinState)
		(= roundRobinState (roundRobin state?))
		(if
			(and
				(or
					(and
						(== (event type?) mouseDown)
						(self onMe: (event x?) (event y?))
					)
					(and
						(== (event type?) keyDown)
						(OneOf (event message?) ESC ENTER)
					)
				)
				(not titleCued)
				(!= roundRobinState 7)
			)
			(event claimed: 1)
			(if (== (roundRobin register?) 3)
				(theTitle cue:)
			else
				(roundRobin changeState: 7)
			)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb)
		(return FALSE)
	)
	
	(method (cue)
		(super cue:)
		(self stopUpd:)
	)
)

(instance theTitle of Prop
	(properties
		x 10
		y 138
		view vCharSel
		loop 3
	)
	
	(method (handleEvent event)
		(if
			(and
				titlePlateOnScreen
				(or
					(and
						(== (event type?) mouseDown)
						(self onMe: (event x?) (event y?))
					)
					(and
						(== (event type?) keyDown)
						(OneOf (event message?) ESC ENTER)
					)
				)
			)
			(event claimed: TRUE)
			(self cue:)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb)
		(return FALSE)
	)
	
	(method (cue)
		(super cue:)
		(= heroType cel)
		(= titleCued TRUE)
		(roundRobin state: 9 cue:)
		(self
			setLoop: 2
			x: [titleX (+ cel 3)]
			y: [titleY (+ cel 3)]
		)
	)
)

(instance roundRobin of Script
	(method (doit)
		(cond 
			((< howFast fast) 0)
			((and fightSoundCued (== (fightChar cel?) 2))
				(theMusic2
					number: sUnsheathe
					setVol: 127
					loop: 1
					play:
				)
				(= fightSoundCued FALSE)
			)
			((and thiefSoundCued (== (thiefChar cel?) 2))
				(theMusic2
					number: sSwoosh
					setVol: 127
					loop: 1
					play:
				)
				(= thiefSoundCued FALSE)
			)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame setCursor: theCursor FALSE)
				(Bset fHideCursor)
				(= seconds 6)
			)
			(1
				(Load SOUND sUnsheathe)
				(= register 1)
				(theTitle loop: 0)
				(= seconds 3)
			)
			(2
				(= register 1)
				(= seconds 0)
				(if (Btst fHideCursor)
					(Bclr fHideCursor)
					(theGame setCursor: theCursor TRUE 50 150)
				)
				(SelectedCharacter FIGHTER)
				(= titlePlateOnScreen TRUE)
				(= fightSoundCued TRUE)
				(if (< howFast fast)
					(fightChar setCel: 1)
					(= ticks 20)
				else
					(fightChar setCycle: EndLoop self)
				)
			)
			(3
				(= register 1)
				(= seconds 3)
			)
			(4
				(if (> howFast medium)
					;Macintosh change
					(Load SOUND sTeleport);sTwinkle)
				)
				(= register 2)
				(= seconds 0)
				(SelectedCharacter MAGIC_USER)
				(if (< howFast fast)
					(mageChar setCel: 1)
					(= ticks 10)
				else
					(mageChar setCycle: EndLoop self)
				)
			)
			(5
				(= register 2)
				(mageChar stopUpd:)
				(cast eachElementDo: #stopUpd)
				(if (> howFast medium)
					(mageArm init: setCycle: EndLoop self)
					(theMusic2
						;Macintosh change
						number: sTeleport ;sTwinkle
						setVol: 127
						loop: 1
						play:
					)
				else
					(= ticks 20)
				)
			)
			(6
				(= register 2)
				(= seconds 3)
			)
			(7
				(= seconds 0)
				(= register 3)
				(SelectedCharacter THIEF)
				(= thiefSoundCued TRUE)
				(if (< howFast fast)
					(thiefChar setCel: 1)
					(= ticks 10)
				else
					(Load SOUND sSwoosh)
					(thiefChar setCycle: EndLoop self)
				)
			)
			(8
				(= seconds (= register 3))
			)
			(9
				(= state 1)
				(self cue:)
			)
			(10
				(if
					(or
						(and (== heroType FIGHTER) (fightChar cycler?))
						(and (== heroType MAGIC_USER) (mageChar cycler?))
						(and (== heroType THIEF) (thiefChar cycler?))
					)
					0
				else
					(self cue:)
				)
			)
			(11
				(switch (theTitle cel?)
					(FIGHTER
						(fightChar addToPic:)
					)
					(MAGIC_USER
						(mageChar addToPic:)
					)
					(THIEF
						(thiefChar addToPic:)
					)
				)
				(theGame setCursor: theCursor FALSE)
				(Bset fHideCursor)
				(= cycles 5)
			)
			(12
				(curRoom drawPic: (curRoom picture?) PIXELDISSOLVE)
				(= seconds 5)
				(theMusic2
					number: sTeleport
					setVol: 127
					loop: 1
					play:
				)
			)
			(13
				(curRoom newRoom: 203)
			)
		)
	)
)
