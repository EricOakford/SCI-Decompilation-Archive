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
	local0
	local1
	local2
	local3
	[local4 6] = [18 202 201 18 212 201]
	[local10 6] = [146 156 146 153 160 153]
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
			(mageChar setCel: 0 cue:)
		else
			(mageArm cel: 5 stopUpd:)
			(mageChar setCycle: BegLoop mageChar)
		)
	)
	(theTitle
		setLoop: 1
		cel: charType
		x: [local4 charType]
		y:
			[local10 (if (thiefChar cel?)
				(if (< howFast fast)
					(thiefChar setCel: 0 cue:)
				else
					(thiefChar setCycle: BegLoop thiefChar)
				)
			)]
	)
	(if
		(or
			(theTitle onMe: (= evt (Event new:)))
			(not local2)
		)
		(Bclr fHideCursor)
		(theGame setCursor: ARROW_CURSOR TRUE
			(switch charType
				(0 50)
				(1 140)
				(else  235)
			) 150
		)
	)
	(evt dispose:)
)

(instance selChar of Room
	(properties
		picture 905
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
			loop: (if (== prevRoomNum 203) 0 else 3)
			init:
		)
		(roundRobin start: (if (== prevRoomNum 203) 2 else 0))
		(self setScript: roundRobin)
		(if (and (not (cSound handle?)) (!= (cSound number?) 61))
			(cSound loop: -1 number: 61 play:)
		)
	)
	
	(method (dispose)
		(= useSortedFeatures TRUE)
		(= pMouse PseudoMouse)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (or (event claimed?) local3)
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
					(script
						state:
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
		view 526
		signal skipCheck
	)
	
	(method (init)
		(super init:)
		(if (< howFast 2)
			(self view: 206)
		else
			(self view: 526)
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
				(not local3)
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
		view 527
		signal skipCheck
	)
	
	(method (init)
		(super init:)
		(if (< howFast fast)
			(self view: 207)
		else
			(self view: 527)
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
				(not local3)
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
		view 527
		loop 1
		priority 15
		signal (| skipCheck fixPriOn)
	)
	
	(method (doVerb)
		(return 0)
	)
)

(instance thiefChar of Prop
	(properties
		x 248
		y 130
		view 528
		signal skipCheck
	)
	
	(method (init)
		(super init:)
		(if (< howFast fast)
			(self view: 208)
		else
			(self view: 528)
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
				(not local3)
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
		view 506
		loop 3
	)
	
	(method (handleEvent event)
		(if
			(and
				local2
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
		(= local3 1)
		(roundRobin state: 9 cue:)
		(self
			setLoop: 2
			x: [local4 (+ cel 3)]
			y: [local10 (+ cel 3)]
		)
	)
)

(instance roundRobin of Script
	(properties)
	
	(method (doit)
		(cond 
			((< howFast 2) 0)
			((and local0 (== (fightChar cel?) 2))
				(spareSound number: 993 setVol: 127 loop: 1 play:)
				(= local0 0)
			)
			((and local1 (== (thiefChar cel?) 2))
				(spareSound number: 109 setVol: 127 loop: 1 play:)
				(= local1 0)
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
				(Load SOUND 993)
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
				(= local2 1)
				(= local0 1)
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
					(Load SOUND 992)
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
					(spareSound number: 992 setVol: 127 loop: 1 play:)
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
				(= local1 1)
				(if (< howFast fast)
					(thiefChar setCel: 1)
					(= ticks 10)
				else
					(Load SOUND 109)
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
				(curRoom drawPic: (curRoom picture?) 9)
				(= seconds 5)
				(spareSound number: 28 setVol: 127 loop: 1 play:)
			)
			(13
				(curRoom newRoom: 203)
			)
		)
	)
)
