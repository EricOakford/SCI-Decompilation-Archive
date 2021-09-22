;;; Sierra Script 1.0 - (do not remove this comment)
(script# 530)
(include game.sh)
(use Main)
(use Intrface)
(use Jump)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm530 0
)
(synonyms
	(palm bureau)
	(blade dope)
)

(local
	local0
	onOtherSide
	local2
	ropeState
	[msgBuf 44]
	[titleBuf 22]
)
(instance rm530 of Room
	(properties
		picture 530
		horizon 1
	)
	
	(method (init)
		(Load VIEW 813)
		(Load SOUND 4)
		(Load SCRIPT JUMP)
		(cls)
		(super init:)
		(self setScript: RoomScript)
		(aRope init:)
		(if (!= prevRoomNum 525)
			(music number: 599 loop: musicLoop play:)
		)
		(cond 
			((== prevRoomNum 535)
				(= playingAsPatti TRUE)
				(= onOtherSide TRUE)
				(= ropeState 531)
				(= currentEgoView 802)
				(NormalEgo loopS)
				(ego setStep: 2 1 posn: 215 75 init:)
				(aRope posn: 227 72 setCycle: EndLoop RopeScript)
			)
			((== prevRoomNum 540)
				(= playingAsPatti TRUE)
				(= onOtherSide TRUE)
				(= ropeState 531)
				(= currentEgoView 802)
				(NormalEgo loopS)
				(ego setStep: 2 1 posn: 31 78 init:)
				(aRope posn: 227 72 setCel: 255 stopUpd:)
			)
			(else
				(= playingAsPatti TRUE)
				(= currentEgoView 800)
				(Load VIEW 531)
				(Load VIEW 532)
				(Load VIEW 533)
				(Load VIEW 534)
				(Load VIEW 804)
				(Load VIEW 20)
				(Load VIEW 27)
				(Load SOUND 531)
				(Load SOUND 530)
				(Load SOUND 12)
				(Load SOUND 599)
				(music number: 4 loop: 1 play:)
				(= currentStatus 530)
				(ego
					ignoreActors:
					ignoreHorizon:
					view: 804
					setLoop: 0
					setCycle: Forward
					setStep: 1 8
					setPri: 12
					illegalBits: 0
					posn: 170 -99
					init:
				)
				(RoomScript changeState: 1)
			)
		)
	)
)

(instance RoomScript of Script
	(method (doit)
		(cond 
			(
				(and
					(== (ego view?) 534)
					(== (ego loop?) 1)
					(== (ego cel?) 1)
				)
				(soundFX number: 530 loop: 1 play:)
			)
			(
			(and (& (ego onControl:) cBLUE) (== currentStatus egoNORMAL))
				(= currentStatus egoFALLING)
				(ego posn: (- (ego x?) 25) (ego y?))
				(self changeState: 65)
			)
			(
			(and (& (ego onControl:) cLGREY) (== currentStatus egoNORMAL))
				(= currentStatus egoFALLING)
				(ego posn: (+ (ego x?) 25) (ego y?))
				(self changeState: 65)
			)
			(
			(and (& (ego onControl:) cBROWN) (== currentStatus egoNORMAL))
				(= currentStatus egoFALLING)
				(ego posn: (ego x?) (+ (ego y?) 5))
				(self changeState: 65)
			)
			((& (ego onControl:) cLGREEN)
				(curRoom newRoom: 540)
			)
			(
				(and
					(== ropeState 3)
					(or (!= 142 (ego x?)) (!= 128 (ego y?)))
				)
				(= ropeState 531)
				(self changeState: 58)
			)
			((and (== currentStatus egoNORMAL) (not (Btst fDrankRiverWater)) (not onOtherSide))
				(cond 
					((== roomSeconds 30)
						(++ roomSeconds)
						(Print 530 0)
					)
					((== roomSeconds 60)
						(++ roomSeconds)
						(Print 530 1)
					)
					((or (> roomSeconds 90) (== ropeState 4))
						(self changeState: 12)
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(ego
					ignoreActors:
					setStep: 1 1
					setMotion: JumpTo 170 155 self
				)
			)
			(2
				(ego setLoop: 1 setCel: 0)
				(music number: 12 loop: 1 play:)
				(ShakeScreen 3 shakeSDown)
				(= seconds 4)
			)
			(3
				(Print 530 64)
				(= seconds 4)
			)
			(4
				(Print 530 65)
				(= seconds 4)
			)
			(5
				(ego setCycle: EndLoop self)
				(addToPics add: atpTits doit:)
			)
			(6
				(music number: 599 loop: musicLoop play:)
				(ego posn: 170 155 cel: 1)
				(NormalEgo 2)
				(= currentStatus egoNORMAL)
				(theGame setSpeed: saveSpeed)
			)
			(7
				(if (not (Btst fGotPot))
					(Bset fGotPot)
					(theGame changeScore: 10)
				)
				(Ok)
				(Print 530 66)
				(HandsOff)
				(= local2 0)
				(ego
					view: 531
					cycleSpeed: 1
					setStep: 2 1
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(8
				(ego
					cycleSpeed: 0
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo (Random 111 195) (ego y?) self
				)
			)
			(9
				(= cycles 11)
				(if (> 4 (++ local2))
					(= state 7)
				)
			)
			(10
				(ego
					cycleSpeed: 1
					setLoop: 0
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(11
				(NormalEgo)
				(= ropeState 1)
				(ego loop: 2 get: iMarijuana)
				(Print 530 67)
			)
			(12
				(HandsOff)
				(= currentStatus egoDYING)
				(Print 530 68)
				(ego loop: 2)
				(music fade:)
				(= seconds 3)
			)
			(13
				(ego view: 804 loop: 1 setCel: 255 setCycle: BegLoop self)
			)
			(14
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: 537
					register: (Format @msgBuf 530 69)
					next: (Format @titleBuf 530 70)
				)
			)
			(15
				(Ok)
				(HandsOff)
				(ego setMotion: MoveTo 151 142 self)
				(music fade:)
			)
			(16
				(ego view: 531 loop: 8 cel: 0 setCycle: EndLoop self)
				(= seconds 3)
			)
			(17
				(theGame changeScore: -50)
				(if (== currentStatus 535)
					(Print 530 71 #at -1 10 #icon 20 0 0)
					(= state 24)
				else
					(Print 530 72 #at -1 10 #icon 20 0 0)
				)
				(= seconds 3)
			)
			(18
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(19
				(Print 530 73 #dispose #at -1 10)
				(= local2 0)
				(ego loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(20
				(ego loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(21
				(if (> 3 (++ local2)) (= state 19))
				(= cycles 20)
			)
			(22
				(ego loop: 5 cel: 0 setCycle: Forward)
				(= cycles (* 3 (NumCels ego)))
			)
			(23
				(ego loop: 6 cel: 0 setCycle: EndLoop self)
			)
			(24
				(ego loop: 7 cel: 0 setCycle: EndLoop)
				(= cycles 44)
			)
			(25
				(Print 530 74 #dispose #at -1 10)
				(music number: 531 loop: -1 play:)
				(= seconds 3)
			)
			(26
				(theGame setSpeed: 6)
				(ego
					view: 533
					posn: (ego x?) (- (ego y?) 26)
					cycleSpeed: 1
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(27
				(ego
					put: iMarijuana -1
					setLoop: 1
					cel: 0
					illegalBits: 0
					ignoreActors:
					setPri:
					setStep: 1 1
					setCycle: Forward
					setMotion: MoveTo 232 91 self
				)
				(= cycles 11)
			)
			(28
				(Print 530 75 #dispose #at -1 10)
				(= cycles 11)
			)
			(29
				(Print 530 76 #dispose #at -1 10)
			)
			(30
				(Print 530 77 #dispose #at -1 10)
				(ego setPri: 2 setMotion: MoveTo 49 91 self)
				(= cycles 22)
			)
			(31
				(Print 530 78 #dispose #at -1 10)
				(= cycles 22)
			)
			(32
				(Print 530 79 #dispose #at -1 144)
			)
			(33
				(music fade:)
				(Print 530 80 #dispose #at -1 10)
				(= seconds 3)
			)
			(34
				(= seconds (= cycles 0))
				(Print
					(Format @msgBuf 530 81 expletive)
					#dispose
					#at -1 10
				)
				(music number: 4 loop: 1 play:)
				(ego setLoop: 2 cel: 0 cycleSpeed: 0 setCycle: EndLoop self)
				(= state 65)
			)
			(35
				(Ok)
				(HandsOff)
				(Print 530 82)
				(if (>= filthLevel 3)
					(Print 530 83 #at -1 144)
				)
				(= currentStatus 533)
				(ego
					view: 532
					setLoop: 0
					illegalBits: 0
					posn: 129 116
					setPri: 11
					cycleSpeed: 1
					moveSpeed: 1
					setMotion: MoveTo 128 47 self
				)
			)
			(36
				(= currentStatus 536)
				(User canInput: TRUE)
			)
			(37
				(Ok)
				(HandsOff)
				(ego setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(38
				(ego setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(39
				(ego setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(40
				(ego get: 19 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(41
				(theGame changeScore: 25)
				(Print 530 84)
				(if (>= filthLevel 3)
					(Print 530 85 #at -1 144)
				)
				(User canInput: TRUE)
			)
			(42
				(Ok)
				(HandsOff)
				(ego
					cycleSpeed: 1
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 129 116 self
				)
			)
			(43
				(ego posn: 128 132)
				(NormalEgo loopN)
				(= currentStatus 0)
			)
			(44
				(Ok)
				(HandsOff)
				((Inventory at: iMarijuana) view: 27)
				(Format ((Inventory at: iMarijuana) name?) 530 86)
				(theGame changeScore: 100)
				(Print 530 87 #at -1 10 #icon 20 0 0)
				(= seconds 3)
			)
			(45
				(Print 530 88)
				(= ropeState 2)
				(NormalEgo)
			)
			(46
				(HandsOff)
				(Ok)
				(= currentStatus 531)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 141 129 self
				)
			)
			(47
				(music stop:)
				(ego
					view: 534
					posn: 142 128
					setLoop: 1
					cel: 0
					setCycle: Forward
				)
				(= cycles (* 3 (- (NumCels ego) 1)))
			)
			(48
				(ego setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(49
				(if (> 2 (++ local0))
					(= state 46)
				)
				(= seconds 3)
			)
			(50
				(Print 530 89 #at -1 10)
				(ego setLoop: 1 setCycle: Forward)
				(= cycles (* 3 (- (NumCels ego) 1)))
			)
			(51
				(ego setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(52
				(theGame changeScore: 20)
				(Print 530 90
					#at 10 5
					#width 290
				)
				(= ropeState 3)
				(soundFX stop:)
				(music play:)
				(HandsOn)
			)
			(53
				(= ropeState 4)
				(theGame changeScore: 20)
				(Ok)
				(HandsOff)
				(ego
					view: 534
					posn: 141 129
					setPri: 9
					setLoop: 3
					setCycle: Forward
				)
				(aRope
					view: 530
					setLoop: 0
					cel: 0
					posn: 227 72
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(54
				(aRope stopUpd:)
				(= cycles 10)
			)
			(55
				(Print 530 91 #at 10 5 #width 290)
				(NormalEgo 1)
				(ego put: iMarijuana -1)
				(= currentStatus egoNORMAL)
			)
			(56
				(HandsOff)
				(Ok)
				(ego view: 534 setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(57
				(theGame changeScore: 50)
				(Print 530 92 #at 10 5 #width 290)
				(= currentEgoView 803)
				((Inventory at: iDress) view: 31)
				(NormalEgo 2)
			)
			(58
				(HandsOff)
				(ego posn: 141 129)
				(NormalEgo 0)
				(aRope
					view: 530
					posn: 227 72
					cycleSpeed: 1
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(59
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: 27
					register: (Format @msgBuf 530 93)
					next: (Format @titleBuf 530 94)
				)
			)
			(60
				(Ok)
				(HandsOff)
				(= currentStatus 532)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 141 129 self
				)
			)
			(61
				(if (!= currentEgoView 803)
					(self cue:)
				else
					(Print 530 95 #at -1 10)
					(ego
						view: 534
						setLoop: 5
						cel: 0
						cycleSpeed: 1
						setCycle: EndLoop self
					)
				)
			)
			(62
				(ego
					view: 534
					setLoop: 6
					cel: 0
					posn: 149 107
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(63
				(Print 530 96)
				(music fade:)
				(= seconds 3)
			)
			(64
				(curRoom newRoom: 535)
			)
			(65
				(HandsOff)
				(Print
					(Format @msgBuf 530 81 expletive)
					#at -1 10
					#dispose
				)
				(music number: 4 loop: 1 play:)
				(ego
					view: 813
					setLoop: (if onOtherSide 1 else 0)
					illegalBits: 0
					ignoreActors:
					setPri: 5
					setStep: 1 8
					cel: 0
					cycleSpeed: 0
					setCycle: EndLoop self
				)
			)
			(66
				(ego setMotion: theJump)
				(theJump y: 300)
			)
			(67
				(cls)
				(if (or (== currentStatus 534) (== currentStatus 535))
					(Print 530 97)
				else
					(Print 530 98)
				)
				(if debugging
					(NormalEgo)
					(= currentStatus egoNORMAL)
					(if onOtherSide
						(ego posn: 210 77 setStep: 2 1)
					else
						(ego posn: 159 158)
					)
				else
					(theGame setScript: (ScriptID DYING))
					((ScriptID DYING)
						caller: 814
						register: (Format @msgBuf 530 99)
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
			((Said 'make,weave/blade,hemp')
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(onOtherSide
						(Print 530 2)
					)
					(else
						(switch ropeState
							(0 (Print 530 3))
							(1 (self changeState: 44))
							(else  (Print 530 4))
						)
					)
				)
			)
			((and (>= ropeState 4) (Said 'unfasten'))
				(Print 530 5)
			)
			((or (Said 'hemp/boulder') (Said 'throw/blade,hemp'))
				(if (!= currentStatus egoNORMAL)
					(GoodIdea)
				else
					(switch ropeState
						(0
							(Print 530 6)
						)
						(1
							(Print 530 7)
							(ego put: iMarijuana curRoomNum)
							(= ropeState 0)
						)
						(2
							(if (not (& (ego onControl:) cGREEN))
								(Print 530 8)
							else
								(self changeState: 46)
							)
						)
						(3
							(ItIs)
						)
						(4
							(ItIs)
						)
						(531
							(Print 530 9)
						)
					)
				)
			)
			((Said 'attach/hemp>')
				(cond 
					((Said '//coconut')
						(Print 530 10)
					)
					((!= currentStatus 531)
						(event claimed: TRUE)
						(Print 530 11)
					)
					((Said '/[/noword]')
						(Print 530 12)
					)
					((Said '//palm')
						(switch ropeState
							(2
								(Print 530 11)
							)
							(3
								(self changeState: 53)
							)
							(4
								(ItIs)
							)
							(531
								(Print 530 13)
							)
							(else
								(Print 530 14)
							)
						)
					)
				)
			)
			(
				(or
					(Said 'make/belt,throw,belt,barstool,belt')
					(Said 'attach/hemp/i,self,entertainer')
					(Said 'use/dress')
					(Said 'break/skirt,cloth,dress')
				)
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((== currentEgoView 803)
						(Print 530 15)
					)
					((< ropeState 3)
						(Print 530 16)
					)
					((< ropeState 4)
						(Print 530 17)
					)
					((> ropeState 4)
						(Print 530 18)
					)
					(else
						(self changeState: 56)
					)
				)
			)
			(
				(or
					(Said 'cross/canyon,hemp')
					(Said 'cross//canyon,hemp')
					(Said 'bang,go,grab,exit,use,climb/hemp,belt')
					(Said 'bang,go,grab,exit,use,climb//hemp,belt')
				)
				(cond 
					((!= currentStatus egoNORMAL)
						(Print 530 19)
					)
					(onOtherSide
						(Print 530 20)
					)
					((< ropeState 4)
						(Print 530 21)
					)
					((> 129 (ego y?))
						(NotClose)
					)
					(else
						(self changeState: 60)
					)
				)
			)
			((and onOtherSide (Said '/hemp'))
				(Print 530 22)
			)
			((and ropeState (Said 'test,look/hemp,knot'))
				(Print 530 23)
			)
			((and (== ropeState 3) (Said 'use,climb,throw/hemp'))
				(Print 530 24)
			)
			((Said 'climb/palm')
				(cond 
					((== currentStatus 536)
						(self changeState: 42)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(onOtherSide
						(Print 530 25)
					)
					((& (ego onControl:) cMAGENTA)
						(Print 530 26)
					)
					((not (& (ego onControl:) cRED))
						(Print 530 27)
					)
					(else
						(self changeState: 35)
					)
				)
			)
			((and (== currentStatus 536) (Said 'go,climb<down'))
				(self changeState: 42)
			)
			((Said 'pick,get/coconut')
				(cond 
					((ego has: iCoconuts)
						(Print 530 28)
					)
					((!= currentStatus 536)
						(Print 530 29)
					)
					(else
						(self changeState: 37)
					)
				)
			)
			((Said 'climb[<down]/boulder,canyon,ledge')
				(Print 530 30)
				(Print 530 31 #at -1 144)
			)
			((Said 'climb<up[/boulder,canyon,ledge]')
				(Print 530 32)
			)
			((or (Said 'look<down') (Said 'look/cliff,edge,canyon'))
				(Print 530 33)
			)
			(
				(and
					(not onOtherSide)
					(or
						(Said 'look/air,hose')
						(Said 'climb/cliff,cliff,cliff')
						(Said 'look<up')
					)
				)
				(Print 530 34)
				(Print 530 35)
			)
			((Said 'pick,get/blade,bush,bush,hemp')
				(if (!= currentStatus egoNORMAL)
					(GoodIdea)
				else
					(switch ropeState
						(0
							(if (not (& (ego onControl:) cCYAN))
								(NotClose)
							else
								(self changeState: 7)
							)
						)
						(1
							(Print 530 36)
						)
						(2
							(Print 530 37)
						)
						(else
							(Print 530 38)
						)
					)
				)
			)
			(
				(or
					(Said 'make,drag/blade')
					(Said 'burn,smoke/bush,bush,blade')
				)
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (ego has: iMarijuana))
						(Print 530 39)
					)
					((> ropeState 1)
						(Print 530 40)
					)
					(else
						(= currentStatus 534)
						(self changeState: 15)
					)
				)
			)
			(
				(or
					(Said 'backdrop/blade,bush/lip')
					(Said 'eat,eat/bush,bush,blade')
				)
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (ego has: iMarijuana))
						(Print 530 41)
					)
					((> ropeState 1)
						(Print 530 42)
					)
					(else
						(= currentStatus 535)
						(self changeState: 15)
					)
				)
			)
			((Said 'unfasten')
				(Print 530 43)
			)
			((Said 'drag,drag,drag/palm')
				(Print 530 44)
			)
			(
				(and
					(ego has: iCoconuts)
					(or
						(Said 'use,attach/coconut')
						(Said 'use,attach//coconut')
					)
				)
				(Print 530 45)
			)
			((Said 'look>')
				(cond 
					((Said '/boulder')
						(cond 
							((== ropeState 531)
								(Print 530 22)
							)
							((== ropeState 4)
								(Print 530 46)
								(Print 530 47 #at -1 144)
							)
							(else
								(Print 530 48)
							)
						)
					)
					((Said '/carpet') (Print 530 49))
					((Said '/palm')
						(cond 
							(onOtherSide
								(Print 530 50)
							)
							((== currentStatus 536)
								(Print 530 51)
							)
							(else
								(Print 530 52)
							)
						)
					)
					((Said '/coconut')
						(if (>= filthLevel 2)
							(Print 530 53 #at -1 144)
						)
						(cond 
							((ego has: iCoconuts)
								(event claimed: FALSE)
							)
							(onOtherSide
								(Print 530 54)
							)
							(else
								(Print 530 55)
								(Print 530 56)
							)
						)
					)
					(
						(and
							(not (ego has: iMarijuana))
							(or (Said '/blade') (Said '/bush,exit'))
						)
						(if (== currentStatus 536)
							(Print 530 57)
						else
							(Print 530 58)
						)
					)
					((Said '/bush,exit')
						(cond 
							((== currentStatus 536)
								(Print 530 57)
							)
							(onOtherSide
								(Print 530 59)
							)
							(else
								(Print 530 60)
							)
						)
					)
					((Said '[/ledge,area]')
						(cond 
							((== currentStatus 536)
								(Print 530 61)
							)
							(onOtherSide
								(Print 530 62)
							)
							(else
								(Print 530 63)
							)
						)
					)
				)
			)
		)
	)
)

(instance aRope of Prop
	(properties
		y 1111
		x 999
		view 530
		loop 1
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setPri: 7)
	)
)

(instance RopeScript of Script
	(method (cue)
		(aRope stopUpd:)
		(Print 530 100 #at 10 5 #width 290)
	)
)

(instance atpTits of PicView
	(properties
		y 157
		x 169
		view 532
		loop 3
		priority 7
		signal ignrAct
	)
)

(instance theJump of Jump
	(method (init)
		(super init: ego RoomScript)
		(self yStep: 2)
	)
)
