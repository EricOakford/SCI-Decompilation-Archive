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
	newProp
	newProp_3
	newProp_2
	newProp_4
	newView_2
	newView_3
	newView_4
	newView_5
	newView_6
	newView_7
	newView_8
	whichChar
	local12
	charPlaque
	local14
	local15
	local16
	local17 =  1
)
(procedure (ToCharAlloc)
	(HandsOff)
	(curRoom newRoom: CHARALLOC)
)

(procedure (localproc_001c)
	(switch whichChar
		(selectFighter
			(charPlaque setCel: 0 posn: 64 127 stopUpd:)
			(mageScript changeState: 0)
			(thiefScript changeState: 0)
			(fighterScript changeState: 1)
		)
		(selectMage
			(charPlaque setCel: 0 posn: 158 127 stopUpd:)
			(fighterScript changeState: 0)
			(thiefScript changeState: 0)
			(if local16
				(newProp_3 setLoop: 1 setCel: 3)
				(newProp_2 show:)
			else
				(mageScript changeState: 1)
			)
		)
		(selectThief
			(charPlaque setCel: 0 posn: 252 127 stopUpd:)
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
			(= heroType MAGE)
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
		((= charPlaque (View new:))
			view: vCharSelect
			setLoop: 0
			setCel: 0
			posn: 0 1000
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
		)
		((= newProp (Prop new:))
			view: vEgoCharSelect
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
			setScript: fighterScript
		)
		((= newProp_2 (Prop new:))
			view: vEgoCharSelect
			setLoop: 3
			setCel: 0
			posn: 158 84
			setPri: 6
			init:
			ignoreActors:
		)
		((= newProp_3 (Prop new:))
			view: vEgoCharSelect
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
			setScript: mageScript
		)
		((= newProp_4 (Prop new:))
			view: vEgoCharSelect
			setPri: 5
			init:
			ignoreActors:
			stopUpd:
			setScript: thiefScript
		)
		((= newView_2 (View new:))
			view: vCharSelect
			setLoop: 1
			setCel: 0
			posn: 83 25
			init:
			ignoreActors:
			stopUpd:
		)
		((= newView_3 (View new:))
			view: vCharSelect
			setLoop: 1
			setCel: 1
			posn: 146 27
			init:
			ignoreActors:
			stopUpd:
		)
		((= newView_4 (View new:))
			view: vCharSelect
			setLoop: 1
			setCel: 2
			posn: 220 27
			init:
			ignoreActors:
			stopUpd:
		)
		((= newView_5 (View new:))
			view: vCharSelect
			setLoop: 1
			setCel: 3
			posn: 65 155
			init:
			ignoreActors:
			stopUpd:
		)
		((= newView_6 (View new:))
			view: vCharSelect
			setLoop: 1
			setCel: 4
			posn: 159 147
			init:
			ignoreActors:
			stopUpd:
		)
		((= newView_7 (View new:))
			view: vCharSelect
			setLoop: 1
			setCel: 6
			posn: 161 163
			init:
			ignoreActors:
			stopUpd:
		)
		((= newView_8 (View new:))
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
		(Joystick 12 30)
	)
	
	(method (doit)
		(cond 
			(
				(and
					local17
					(or
						(== (cSound signal?) 2)
						(== (cSound prevSignal?) 2)
					)
				)
				(= local17 0)
				(cSound stop:)
				(cSound number: (SoundFX 73) loop: -1 play:)
			)
			((and local15 (== (cSound prevSignal?) 3))
				(cSound prevSignal: 0)
				(if (== whichChar 3) (= whichChar 1) else (++ whichChar))
				(= local14 0)
				(localproc_001c)
			)
			(local16 (= local16 0) (SelectCharacter))
		)
		(super doit:)
	)
	
	(method (dispose)
		(Joystick 12 0)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0 temp1 temp2 [temp3 60])
		(if local14
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
						(= local16 1)
					)
				)
			)
			(switch (event type?)
				(mouseDown
					(= temp1 (event x?))
					(= temp2 (event y?))
					(= temp0 (OnControl CMAP temp1 temp2))
					(= local15 0)
					(event claimed: 1)
					(cond 
						((& temp0 cYELLOW) (= whichChar 1) (= local16 1))
						((& temp0 cLMAGENTA) (= whichChar 2) (= local16 1))
						((& temp0 cLRED) (= whichChar 3) (= local16 1))
						(else (event claimed: 0))
					)
					(event claimed: TRUE)
					(= local14 0)
					(localproc_001c)
				)
				(direction
					(switch (event message?)
						(dirW
							(if (== whichChar 1) (= whichChar 3) else (-- whichChar))
						)
						(dirE
							(if (== whichChar 3) (= whichChar 1) else (++ whichChar))
						)
					)
					(event claimed: TRUE)
					(= local15 0)
					(= local14 0)
					(localproc_001c)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance fighterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (= cycles 0))
				(newProp setLoop: 4 setCel: 0 posn: 64 117)
			)
			(1
				(= seconds (= cycles 0))
				(= whichChar selectFighter)
				(newProp setLoop: 0 setCel: 1)
				(= local14 1)
				(= cycles 5)
			)
			(2
				(newProp setCel: 0)
				(= cycles 5)
			)
			(3
				(fighterScript changeState: 1)
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
				(newProp_3 setLoop: 4 setCel: 1 posn: 158 117)
				(newProp_2 stopUpd: hide:)
			)
			(1
				(= seconds (= cycles 0))
				(= whichChar selectMage)
				(newProp_3 setLoop: 1 setCel: 0)
				(= local14 1)
				(= cycles 3)
			)
			(2
				(newProp_3 setCel: -1 setCycle: EndLoop self)
			)
			(3
				(newProp_2 show: setCycle: Forward startUpd:)
				(= seconds 3)
			)
			(4 (self changeState: 3))
		)
	)
)

(instance thiefScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (= cycles 0))
				(newProp_4 setLoop: 4 setCel: 2 posn: 252 117)
			)
			(1
				(= seconds (= cycles 0))
				(= whichChar selectThief)
				(newProp_4 setLoop: 2 setCel: 0)
				(= local14 1)
				(= seconds 2)
			)
			(2
				(newProp_4 setCel: -1 setCycle: EndLoop)
				(= seconds 2)
			)
			(3
				(newProp_4 setCycle: BegLoop self)
			)
			(4 (thiefScript changeState: 1))
		)
	)
)

(instance selScript of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(== state 3)
				(not local15)
				(== (cSound prevSignal?) 3)
			)
			(cSound prevSignal: 0)
			(= local14 1)
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
				(++ local12)
				(newView_2 hide:)
				(newView_3 hide:)
				(newView_4 hide:)
				(= cycles 3)
			)
			(2
				(newView_2 show:)
				(newView_3 show:)
				(newView_4 show:)
				(= cycles 3)
			)
			(3
				(cSound prevSignal: 0)
				(if (< local12 2) (self changeState: 1))
			)
		)
	)
)
