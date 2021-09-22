;;; Sierra Script 1.0 - (do not remove this comment)
(script# 260)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm260 0
)
(synonyms
	(maller babe)
	(man man)
)

(local
	puttingDownTowel
	noSwimMsg
	vendorHere
	[local3 2]
	[msgBuf 40]
	[titleBuf 22]
)
(instance rm260 of Room
	(properties
		picture 260
		horizon 66
		west 250
	)
	
	(method (init &tmp temp0)
		(super init:)
		(self setScript: RoomScript)
		(if (and (Btst fRolledOut) (== tawniState 0))
			(= tawniState 1)
			(= vendorView 267)
		)
		(if
			(and
				(ego has: iWood)
				(< tawniState 6)
				(== currentEgoView 707)
				(== currentStatus egoNATIVE)
				(or
					(== ((inventory at: iWood) view?) 22)
					(== ((inventory at: iWood) view?) 34)
				)
			)
			(= tawniState 5)
		)
		(if (== currentStatus egoAUTO)
			(= tawniState 3)
			(= vendorView 264)
		)
		(if (< prevRoomNum curRoomNum)
			(ego posn: 1 174 loop: 0)
		)
		(NormalEgo)
		(switch tawniState
			(0
				(Load VIEW 712)
				(aLizard init:)
			)
			(1
				(ego observeControl: cYELLOW)
			)
			(2
				(aTawni setLoop: 2 setCel: 255)
				(TawniScript changeState: 8)
				(ego observeControl: cYELLOW)
				(-- tawniState)
			)
			(3
				(Load VIEW (- 263 (* 5 (>= filthLevel 3))))
				(Load VIEW 264)
				(Load SOUND 8)
				(Load SOUND 9)
				(Load SOUND 260)
				(Load SOUND 261)
				(aTawni
					illegalBits: 0
					ignoreActors:
					view: (- 263 (* 5 (>= filthLevel 3)))
					loop: 0
					cel: 0
				)
				(ego
					illegalBits: 0
					ignoreActors:
					view: currentEgoView
					loop: 0
					posn: 132 165
					setMotion: MoveTo 138 165
				)
				(RoomScript changeState: 29)
			)
			(4
				(aTawni init:)
				(ego observeControl: cYELLOW)
			)
			(5
				(Load VIEW 707)
				(Load VIEW 22)
				(Load VIEW 34)
				(HandsOff)
				(Bset fCursorHidden)
				(= vendorView 707)
				(= currentStatus egoNATIVE)
				(aTawni init:)
			)
			(6
				(= vendorView -1)
				(aTowel init:)
			)
			(7
				(aTowel init: hide:)
				(= vendorView -1)
			)
		)
		(if (and (not playingAsPatti) (>= tawniState 6))
			(Load VIEW (+ 701 larryBuffed))
			(Load VIEW 261)
			(Load VIEW 709)
			(aLizard init:)
		)
		(if (!= tawniState 5)
			(ego init:)
		)
		(if (and tawniState (< tawniState 6))
			(aTowel init:)
			(aTawni init:)
			(if (== tawniState 3)
				(TawniScript changeState: 11)
			)
		)
		(if
			(and
				vendorView
				tawniState
				(not programControl)
				(!= vendorView -1)
				(< tawniState 6)
			)
			(aVendor init:)
		)
		(if (and (not programControl) (!= tawniState 3))
			(music number: 260 loop: -1 play:)
		)
		(if
			(and
				(== currentStatus 14)
				(or (== prevRoomNum 266) (== prevRoomNum 265))
			)
			(= currentStatus egoNORMAL)
		)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cGREY)
			(if (not noSwimMsg)
				(= noSwimMsg TRUE)
				(Printf 260 0 currentEgo)
			)
		else
			(= noSwimMsg FALSE)
		)
		(if (and (!= currentStatus egoNORMAL) (!= currentStatus egoDROWNING))
			(ego observeControl: cLBLUE)
		)
		(if
		(and (== (ego onControl:) cLBLUE) (== currentStatus egoNORMAL))
			(self changeState: 5)
		)
	)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0)
			(5
				(= cycles
					(= seconds 0)
				)
				(HandsOff)
				(= currentStatus egoDROWNING)
				(ego
					view: (if playingAsPatti 812 else 712)
					illegalBits: -513
					cycleSpeed: 1
					moveSpeed: 1
					setStep: 2 2
					setCycle: Forward
				)
				(= seconds 3)
			)
			(6
				(ego setMotion: 0 setCycle: EndLoop self)
			)
			(7
				(ego hide:)
				(= seconds 2)
			)
			(8
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: (+ 4 (ego view?))
					register: (Format @msgBuf 260 24 currentEgo)
					next: (Format @titleBuf 260 25)
				)
			)
			(9
				(HandsOff)
				(ego setMotion: MoveTo 126 155 self)
			)
			(10
				(ego view: 709 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(11
				(aTowel hide:)
				(ego get: iTowel setCycle: BegLoop self)
				(= tawniState 7)
				(theGame changeScore: 2)
			)
			(12
				(if puttingDownTowel
					(= puttingDownTowel 0)
					(self cue:)
				else
					(NormalEgo)
				)
			)
			(13
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 151 170 self)
				(LizardScript changeState: 8)
			)
			(14
				(ego
					view: 261
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
					cycleSpeed: 1
				)
			)
			(15
				(aTowel view: 261 loop: 1 posn: 184 170 show: stopUpd:)
				(ego
					view: currentEgoView
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 184 170 self
				)
			)
			(16
				(ego
					cycleSpeed: 1
					view: (+ 701 larryBuffed)
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(17
				(ego view: 261 setLoop: 2 cel: 0 setCycle: EndLoop self)
				(LizardScript changeState: 9)
			)
			(18
				(= currentStatus egoSUNBATHING)
				(User canInput: TRUE)
				(= seconds 5)
			)
			(19
				(Print 260 26)
				(= seconds 5)
			)
			(20
				(Print 260 27)
				(= seconds 5)
			)
			(21
				(if (Btst fGotSuntan)
					(Print 260 28)
					(= seconds 4)
				else
					(Bset fGotSuntan)
					(theGame changeScore: 30)
					(Print 260 29)
					(= seconds 10)
				)
			)
			(22
				(Print 260 30)
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: 259
					register: (Format @msgBuf 260 31)
					next: (Format @titleBuf 260 32)
				)
			)
			(23
				(HandsOff)
				(Ok)
				(= seconds 0)
				(ego view: 261 setCycle: BegLoop self)
			)
			(24
				(ego
					view: (+ 701 larryBuffed)
					setLoop: 0
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(25
				(ego
					cycleSpeed: 0
					view: currentEgoView
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 151 169 self
				)
			)
			(26
				(ego
					cycleSpeed: 1
					view: 709
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(27
				(aTowel hide:)
				(ego setCycle: BegLoop self)
			)
			(28
				(NormalEgo)
				(= currentStatus egoNORMAL)
			)
			(29
				(HandsOff)
				(Bset fCursorHidden)
				(= seconds 0)
				(aTawni setScript: 0 setCycle: EndLoop self)
				(music number: 8 loop: -1 play:)
			)
			(30
				(aTawni hide:)
				(ego
					posn: 153 165
					view: (- 263 (* 5 (>= filthLevel 3)))
					loop: 1
					cel: 0
					setCycle: EndLoop self
					cycleSpeed: 1
				)
			)
			(31
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(32
				(ego viewer: humpCycler setLoop: 3 setCycle: Forward)
			)
			(33
				(Print 260 33)
				(Print 260 34)
				(Print 260 35)
				(music number: 9 loop: 1 play:)
				(ego
					viewer: 0
					cycleSpeed: 1
					loop: 4
					cel: 0
					setCycle: EndLoop self
				)
			)
			(34
				(Print 260 36)
				(= seconds 3)
			)
			(35
				(Print 260 37)
				(Print 260 38)
				(= seconds 3)
			)
			(36
				(Print 260 39)
				(Print 260 40 #at -1 144)
				(= seconds 3)
			)
			(37
				(Print 260 41)
				(Print 260 42)
				(if (>= filthLevel 3) (Print 260 43))
				(Print 260 44)
				(= seconds 3)
			)
			(38
				(Print 260 45)
				(Print 260 46)
				(ego get: iKnife)
				(= newspaperState NSpComing)
				(= showroomState SRshowIsOn)
				(theGame changeScore: 40)
				(= seconds 3)
			)
			(39
				(Print 260 47)
				(Print 260 48)
				(Print 260 49)
				(VendorScript changeState: 10)
				(= seconds 3)
			)
			(40
				(ego setCycle: BegLoop self)
			)
			(41
				(Print 260 50)
				(= seconds 3)
			)
			(42
				(Print 260 51)
				(music number: 8 loop: -1 play:)
				(ego viewer: humpCycler setLoop: 3 setCycle: Forward)
				(= seconds 12)
			)
			(43
				(music fade:)
				(Print 260 52)
				(music number: 261 loop: -1 play:)
				(Print 260 53 #at -1 10)
				(ego
					viewer: 0
					cycleSpeed: 0
					loop: 4
					cel: 0
					setCycle: EndLoop self
				)
			)
			(44
				(aTawni view: 262 loop: 2 setCel: 255 show:)
				(ego loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(45
				(Print 260 54 #at -1 10)
				(ego
					posn: 142 165
					setLoop: 6
					setCycle: Forward
					setStep: 1 1
					setMotion: MoveTo 127 165
				)
				(= cycles 44)
			)
			(46
				(Print (Format @msgBuf 260 55 expletive) #at -1 10)
				(ego cycleSpeed: 1 loop: 7 cel: 0 setCycle: EndLoop self)
			)
			(47
				(Print 260 56 #at -1 10)
				(ego viewer: humpCycler loop: 8 setCycle: Forward)
				(= seconds 3)
			)
			(48
				(Print 260 57 #at -1 10)
				(= cycles 11)
			)
			(49
				(aTawni view: 262 setScript: TawniScript)
				(TawniScript changeState: 9)
				(ego viewer: 0 loop: 9 cel: 0 setCycle: EndLoop self)
			)
			(50
				(Print 260 58 #at -1 10)
				(= seconds 3)
			)
			(51
				(music number: 9 loop: 1 play: self)
				(Print 260 59)
				(NormalEgo loopS)
				(Bclr fCursorHidden)
				(ego observeControl: cYELLOW)
				(= tawniState 4)
				(= currentStatus egoNORMAL)
			)
			(52
				(music number: 260 loop: -1 play:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (super handleEvent: event))
				(not (event claimed?))
				modelessDialog
				(== (event message?) ENTER)
				(== (event type?) keyDown)
			)
			(event claimed: TRUE)
			(cls)
			(self cue:)
		)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'carve,carve')
				(Print 260 1)
			)
			((Said 'get/crab')
				(Print 260 2)
			)
			(
				(or
					(Said '(get<on),throw,use,lie,bang/towel')
					(Said '(bath<air),sunbath')
					(Said 'get/tan,booth')
					(Said '/(bath<air),sunbath')
					(Said 'drain/cloth')
					(Said 'lie,bang/down[<on]/towel')
				)
				(cond 
					((== currentStatus egoSUNBATHING)
						(YouAre)
					)
					((== currentStatus egoNATIVE)
						(Print 260 3)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((== tawniState 1)
						(Print 260 4)
					)
					((== tawniState 4)
						(Print 260 5)
						(Print 260 6)
					)
					((== tawniState 6)
						(Ok)
						(= puttingDownTowel TRUE)
						(self changeState: 9)
					)
					((not (ego has: iTowel))
						(DontHave)
					)
					(else
						(Ok)
						(self changeState: 13)
					)
				)
			)
			(
				(or
					(Said 'nightstand,(nightstand<up),(get<off),(get<up)')
					(Said 'sunbath<cease')
					(Said 'exit/towel')
				)
				(if (!= currentStatus egoSUNBATHING)
					(Print 260 7)
				else
					(self changeState: 23)
				)
			)
			((Said '/towel>')
				(cond 
					((ego has: iTowel)
						(event claimed: FALSE)
					)
					(
						(or
							(& (aTowel signal?) actorHidden)
							(not (cast contains: aTowel))
						)
						(Print 260 8)
						(Print 260 9)
						(event claimed: TRUE)
					)
					((Said 'get,grab,rob,(pick<up)')
						(cond 
							((== currentStatus egoNATIVE)
								(Print 260 3)
							)
							((!= currentStatus egoNORMAL)
								(GoodIdea)
							)
							((== tawniState 1)
								(Print 260 10)
							)
							((== tawniState 4)
								(Print 260 11)
								(Print 260 6)
							)
							((!= tawniState 6)
								(Print 260 7)
							)
							(else
								(Ok)
								(self changeState: 9)
							)
						)
					)
					((Said 'look')
						(cond 
							((and (>= tawniState 1) (<= tawniState 5))
								(Print 260 12)
							)
							((< tawniState 6)
								(Print 260 13)
							)
							((== tawniState 6)
								(Print 260 14)
							)
							((== currentStatus egoSUNBATHING)
								(Print 260 15)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			((Said 'look>')
				(cond 
					((Said '/boulder,boulder')
						(Print 260 16)
					)
					((Said '/bay,water,bay')
						(Print 260 17)
						(Print 260 18)
					)
					((or (Said 'down<look') (Said '/beach,down,beach'))
						(Print 260 19)
					)
					((Said '/crab')
						(Print 260 20)
					)
					((Said '[/area]')
						(if (and tawniState (< tawniState 5))
							(Print 260 21)
						else
							(Print 260 22)
						)
						(Print 260 23)
					)
				)
			)
		)
	)
)

(instance aTawni of Actor
	(properties
		y 165
		x 153
		view 262
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setScript: TawniScript ignoreActors:)
	)
	
	(method (handleEvent event &tmp i)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'give,sell,show>')
				(cond 
					((not (& (ego onControl:) cLRED))
						(NotClose)
					)
					((== 707 (ego view?))
						(= i (inventory saidMe:))
						(event claimed: FALSE)
						(cond 
							((Said '[/noword]')
								(Print 260 60)
							)
							((not i)
								(Print 260 61)
							)
							((not (i ownedBy: ego))
								(DontHave)
							)
							((== i (inventory at: iWood))
								(Print 260 62)
								(Print 260 63)
							)
							((== i (inventory at: iSoap))
								(Print 260 64)
								(Print 260 64)
								(Print 260 65)
							)
							((== i (inventory at: iOrchids))
								(Print 260 66)
							)
							(else
								(Print 260 67)
							)
						)
						(Print 260 68)
					)
					((== tawniState 4)
						(Print 260 69)
						(Print 260 70)
						(Print 260 71 #at -1 144)
					)
					(else
						(Print 260 72)
					)
				)
				(event claimed: TRUE)
			)
			((or (Said '//maller>') (Said '/maller>'))
				(cond 
					((> tawniState 4)
						(event claimed: TRUE)
						(Print 260 73)
					)
					((Said 'bang')
						(Print 260 74)
					)
					((Said 'address')
						(cond 
							((not (& (ego onControl:) cLRED))
								(NotClose)
							)
							((== 707 (ego view?))
								(Print 260 69)
								(Print 260 75)
							)
							((== tawniState 4)
								(Print 260 69)
								(Print 260 70)
								(Print 260 71 #at -1 144)
							)
							(else
								(Print 260 76)
							)
						)
					)
					((Said 'look')
						(cond 
							((== 707 (ego view?))
								(Print 260 77)
							)
							((== tawniState 4)
								(Print 260 78)
							)
							((== vendorHere TRUE)
								(Print 260 79)
							)
							((not (& (ego onControl:) cLRED))
								(NotClose)
							)
							(else
								(if (not (Btst fMetTawni))
									(Printf 260 80 introductoryPhrase)
								)
								(HandsOff)
								(Bset fCursorHidden)
								(= tawniState 2)
								(TawniScript changeState: 5)
							)
						)
					)
				)
			)
		)
	)
)

(instance TawniScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 3 7))
			)
			(1
				(aTawni cycleSpeed: 1 setLoop: 1 setCycle: EndLoop)
				(= seconds (Random 1 3))
			)
			(2
				(aTawni setCycle: BegLoop)
				(= seconds (Random 3 5))
			)
			(3
				(aTawni setLoop: (Random 4 5) setCycle: EndLoop)
				(= seconds (Random 3 5))
			)
			(4
				(aTawni setCycle: BegLoop self)
				(= state -1)
			)
			(5
				(= seconds 0)
				(aTawni
					cycleSpeed: 2
					setLoop: 2
					cel: 0
					setCycle: EndLoop self
				)
				(if (>= filthLevel 3) (++ state))
			)
			(6
				(aTawni setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(7
				(if (== tawniState 2)
					(if (not (Btst fMetTawni))
						(Bset fMetTawni)
						(Print 260 81)
					)
					(= currentStatus 14)
					(curRoom newRoom: 266)
				)
			)
			(8
				(= seconds 0)
				(if (>= filthLevel 3)
					(self cue:)
				else
					(aTawni setCycle: BegLoop self)
				)
			)
			(9
				(aTawni setLoop: 2 setCel: 255 setCycle: BegLoop self)
				(= vendorHere 0)
				(= state 0)
			)
			(11 (= seconds 0))
		)
	)
)

(instance aVendor of Actor
	(method (init)
		(super init:)
		(self
			view: vendorView
			setCycle: Walk
			setScript: VendorScript
		)
	)
)

(instance VendorScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aVendor posn: -15 123)
				(switch vendorView
					(707
						(self cue:)
					)
					(264
						(if (<= filthLevel 1)
							(= cycles 99)
						else
							(= cycles 222)
						)
					)
					(else 
						(= seconds (Random 5 11))
					)
				)
			)
			(1
				(= vendorHere TRUE)
				(aVendor
					setLoop: 0
					view: vendorView
					setMotion: MoveTo 137 149 self
				)
				(= cycles 11)
			)
			(2
				(Print 260 85)
				(= cycles 11)
			)
			(3
				(Print 260 85)
				(= cycles 11)
			)
			(4
				(Print 260 86)
			)
			(5
				(if (and (!= tawniState 3) (>= (TawniScript state?) 5))
					(return)
				)
				(Print 260 87)
				(aVendor setLoop: (- (NumLoops aVendor) 1))
				(if (== vendorView 264)
					(aVendor setCycle: Forward)
					(RoomScript changeState: 33)
					(return)
				)
				(aVendor viewer: salesViewer)
				(Print 260 88)
				(switch vendorView
					(267 (Print 260 89))
					(268 (Print 260 90))
					(269 (Print 260 91))
					(707 (Print 260 92))
				)
				(TawniScript changeState: 5)
				(= cycles 30)
			)
			(6
				(switch vendorView
					(267
						(Print 260 93)
					)
					(268
						(Print 260 94)
						(Print 260 95)
					)
					(269
						(Print 260 96)
					)
					(707
						(Print 260 97)
						(Print 260 98 #at -1 144)
					)
				)
				(= cycles 30)
			)
			(7
				(switch vendorView
					(267
						(Print 260 99)
					)
					(268
						(Print 260 100)
					)
					(269
						(Print 260 101)
					)
					(707
						(if (== ((Inventory at: iWood) view?) 22)
							(Print 260 102 #icon 22 0 0)
						else
							(Print 260 103 #icon 34 0 0)
						)
					)
				)
				(= cycles 30)
			)
			(8
				(switch vendorView
					(267
						(Print 260 104)
					)
					(268
						(Print 260 105)
					)
					(269
						(Print 260 106)
						(Print 260 107 #at -1 144)
					)
					(707
						(Printf 260 108 expletive)
					)
				)
				(= cycles 30)
			)
			(9
				(aVendor loop: 0 setCycle: Walk viewer: 0)
				(switch vendorView
					(267
						(Print 260 109)
						(Print 260 110)
						(Print 260 111)
					)
					(268
						(Print 260 112)
					)
					(269
						(Print 260 113)
						(Print 260 114)
					)
					(707
						(Print 260 115)
						(theGame changeScore: 35)
						(ego get: iMoney put: iWood -1)
						(= dollars 20)
						(Print 260 116)
					)
				)
				(TawniScript cue:)
				(= cycles 30)
			)
			(10
				(aVendor
					cycleSpeed: 0
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo -30 (Random 114 163) self
				)
			)
			(11
				(cond 
					((== vendorView 707) (= vendorView -1)
						(= tawniState 6)
						(curRoom newRoom: 250)
					)
					((or (== vendorView 269) (== vendorView 264))
						(= vendorView -1)
					)
					(else
						(++ vendorView)
						(self changeState: 0)
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look/man,man')
				(if (or (< state 1) (> state 10))
					(Print 260 73)
				else
					(Print 260 82)
				)
			)
			((Said 'address/man,man')
				(if (or (< state 1) (> state 10))
					(Print 260 73)
				else
					(Print 260 83)
					(Print 260 84)
				)
			)
		)
	)
)

(instance aTowel of View
	(properties
		y 169
		x 154
		view 262
	)
	
	(method (init)
		(super init:)
		(self setPri: 4 ignoreActors: stopUpd:)
	)
)

(instance aLizard of Actor
	(properties
		view 260
		priority 15
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			setCycle: Walk
			setScript: LizardScript
			setPri: 15
			ignoreActors:
		)
	)
)

(instance LizardScript of Script
	(method (doit)
		(super doit:)
		(if
			(and
				(== state 5)
				(>= (RoomScript state?) 23)
				(<= (RoomScript state?) 28)
			)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Random 0 1)
					(aLizard setLoop: 0 posn: -20 (Random 172 188))
				else
					(aLizard setLoop: 1 posn: 218 198)
				)
				(= seconds (Random 10 20))
			)
			(1
				(aLizard
					setMotion: MoveTo (Random 126 192) (Random 172 188) self
				)
			)
			(2
				(= seconds (Random 2 5))
			)
			(3
				(aLizard
					cycleSpeed: 1
					setLoop: (+ 2 (aLizard loop?))
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(= cycles (Random 3 7))
			)
			(5
				(aLizard
					setLoop: (+ 2 (aLizard loop?))
					cel: 0
					setCycle: Forward
				)
				(if
					(and
						(<= (RoomScript state?) 22)
						(>= (RoomScript state?) 18)
					)
					(= seconds 7)
				else
					(= cycles (Random 5 9))
				)
			)
			(6
				(aLizard
					setLoop: (- (aLizard loop?) 2)
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(7
				(aLizard
					setLoop: (- (aLizard loop?) 2)
					cycleSpeed: 0
					setCycle: Walk
				)
				(if (== 0 (aLizard loop?))
					(aLizard setMotion: MoveTo 218 198 self)
				else
					(aLizard setMotion: MoveTo -55 (Random 172 188) self)
				)
				(= state -1)
			)
			(8
				(= seconds (= cycles 0))
				(aLizard
					setCycle: Walk
					setLoop: 1
					setMotion: MoveTo (- (aLizard x?) 33) 222
				)
			)
			(9
				(if (>= filthLevel 3)
					(aLizard
						setLoop: 0
						posn: -20 180
						setCycle: Walk
						setMotion: MoveTo 148 188 self
					)
				)
			)
			(10
				(aLizard setMotion: MoveTo 192 167 self)
			)
			(11
				(aLizard
					cycleSpeed: 1
					setLoop: 2
					cel: 0
					setCycle: EndLoop self
				)
				(= state 3)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (and state (Said '/lizard>'))
			(cond 
				((Said 'get,grab/')
					(Print 260 117)
				)
				((Said 'look/')
					(cond 
						((== state 0)
							(Print 260 73)
						)
						((and (== state 5) (== currentStatus egoSUNBATHING))
							(Print 260 118)
						)
						(else
							(Print 260 119)
						)
					)
				)
				(else
					(Print 260 120)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance humpCycler of Code
	(method (doit)
		(cond 
			((<= filthLevel 1)
				(ego setCycle: 0)
			)
			((not (Random 0 9))
				(ego cycleSpeed: (Random 0 5))
			)
		)
	)
)

(instance salesViewer of Code
	(method (doit)
		(if (not (Random 0 3))
			(aVendor setCel: (Random 0 (- (NumCels aVendor) 1)))
		)
	)
)
