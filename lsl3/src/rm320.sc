;;; Sierra Script 1.0 - (do not remove this comment)
(script# 320)
(include game.sh)
(use Main)
(use n021)
(use AutoDoor)
(use Intrface)
(use Extra)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm320 0
)
(synonyms
	(man hardy attendant man)
)

(local
	[str 301]
	local301
)
(instance rm320 of Room
	(properties
		picture 320
		south 310
	)
	
	(method (init)
		(if (= dollars 500)
			(Load VIEW 24)
		)
		(Load SOUND 323)
		(super init:)
		(addToPics
			add: atpDeskStuff
			add: atpPencils
			add: atpDCHSign
			add: atpSign
			add: atpFax
			doit:
		)
		(aDoor
			init:
			locked: TRUE
		)
		(if playingAsPatti
			(= lawyerState LSdone)
		)
		(if (< lawyerState LSdone)
			(aRoger init:)
			(if (> machineSpeed 16)
				(aFax init:)
			)
		)
		(self setScript: RoomScript)
		(if (or (== prevRoomNum 323) (== prevRoomNum 324))
			(ego loop: 2 posn: 153 96)
		else
			(ego loop: 3 posn: 156 186)
		)
		(if (!= prevRoomNum 323)
			(music number: 323 loop: musicLoop play:)
		)
		(NormalEgo)
		(ego init:)
	)
	
	(method (newRoom n)
		(cond 
			((and (== lawyerState LSwaiting4Deed) (== (ego edgeHit?) SOUTH))
				(= lawyerState LSdeedReady)
			)
			((and (== lawyerState LSWaiting4Divorce) (== (ego edgeHit?) SOUTH))
				(= lawyerState LSdivorceReady)
			)
		)
		(super newRoom: n)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 1 4)
		(switch (= state newState)
			(0)
			(1)
			(2
				(HandsOff)
				(theGame changeScore: 10)
			)
			(3
				(HandsOff)
				(aDoor locked: FALSE)
				(ego illegalBits: 0 setMotion: MoveTo 233 (ego y?) self)
			)
			(4
				(ego setMotion: MoveTo 233 130 self)
				(if (== currentStatus egoSHOWGIRL)
					(Format @str 320 15)
					(SecretaryScript changeState: 2)
				)
			)
			(5
				(ego setMotion: MoveTo 153 93 self)
			)
			(6
				(ego setMotion: MoveTo 153 87 self)
			)
			(7
				(if (== currentStatus egoSHOWGIRL)
					(curRoom newRoom: 324)
				else
					(curRoom newRoom: 323)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'get/palm')
				(Print 320 0)
			)
			((Said '/equipment')
				(Print 320 1)
				(Print 320 2 #at -1 144)
			)
			((Said '/computer')
				(Print 320 3)
			)
			((Said '/call')
				(Print 320 4)
			)
			((Said 'look>')
				(cond 
					((Said '/palm')
						(Print 320 5)
					)
					((Said '/awning,wall')
						(Print 320 6)
					)
					((Said '/burn,ceiling')
						(Print 320 7)
					)
					((Said '/buffet,buffet,buffet')
						(Print 320 8)
					)
					((Said '/door')
						(cond 
							((& (ego onControl:) cCYAN)
								(Print 320 9)
							)
							((& (ego onControl:) cBLUE)
								(Print 320 10)
							)
							((& (ego onControl:) cRED)
								(Print 320 11)
							)
							(else
								(Print 320 12)
							)
						)
					)
					((Said '[/area]')
						(Print 320 13)
						(if (cast contains: aRoger)
							(Print 320 14)
						)
					)
				)
			)
		)
	)
)

(instance SecretaryScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 2 2)
		(switch (= state newState)
			(0
				(aRoger cycleSpeed: 0 loop: 2 setCel: 0 setCycle: 0)
				(= local301 0)
				(= cycles 0)
				(= seconds (Random 3 6))
			)
			(1
				(if (== local301 0)
					(= local301 (Random 320 327))
				)
				(cond 
					((!= str 0)
						(self changeState: 2)
					)
					((== local301 320)
						(self changeState: 4)
					)
					((== local301 323)
						(self changeState: 6)
					)
					((== local301 321)
						(self changeState: 9)
					)
					(else
						(self changeState: 0)
					)
				)
			)
			(2
				(= local301 322)
				(aRoger loop: 3 setCycle: Forward)
				(= cycles (Random 11 44))
			)
			(3
				(if (== str 0)
					(switch (Random 1 5)
						(1 (Format @str 320 67))
						(2 (Format @str 320 68))
						(3 (Format @str 320 69))
						(4 (Format @str 320 70))
						(5 (Format @str 320 71))
					)
				)
				(Print @str
					#at -1 10
					#title {Roger says}
					#mode teJustCenter
					#icon 321 5 0
				)
				(User canInput: TRUE)
				(if (== (RoomScript state?) 2)
					(RoomScript cue:)
				)
				(= str 0)
				(= cycles 22)
				(= state -1)
			)
			(4
				(aRoger loop: 2 setCycle: Forward cycleSpeed: 1)
				(= cycles (Random 9 19))
			)
			(5
				(aRoger setCel: 0)
				(= cycles (Random 9 19))
				(if (Random 0 2)
					(= state 3)
				else
					(= state -1)
				)
			)
			(6
				(aRoger cycleSpeed: 2 loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(7
				(= cycles (Random 9 19))
			)
			(8
				(aRoger setCycle: BegLoop self)
				(= state -1)
			)
			(9
				(aRoger cycleSpeed: 1 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(10
				(aRoger cycleSpeed: 1 loop: 1 setCycle: Forward)
				(= cycles (Random 9 19))
			)
			(11
				(aRoger setCel: 0)
				(= cycles (Random 9 19))
				(if (Random 0 3)
					(= state 9)
				)
			)
			(12
				(aRoger loop: 0 setCel: 255 setCycle: BegLoop self)
				(= state -1)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look/man') (Print 320 16))
			(
				(or
					(Said '/appointment')
					(Said '//appointment')
					(Said 'address')
				)
				(cond 
					((not (& (ego onControl:) cMAGENTA))
						(NotClose)
					)
					((and (!= currentStatus egoNORMAL) (!= currentStatus egoSHOWGIRL))
						(GoodIdea)
					)
					((!= local301 0)
						(Print 320 17)
					)
					(else
						(User canInput: FALSE)
						(switch lawyerState
							(LSbusy
								(Print 320 18)
								(Format @str 320 19)
								(self changeState: 2)
							)
							(LSfree
								(Print 320 18)
								(Format @str 320 20)
								(self changeState: 2)
							)
							(LSwaiting4Deed
								(Print 320 21)
								(Format @str 320 22)
								(self changeState: 2)
							)
							(LSdeedReady
								(Print 320 23)
								(Format @str 320 24)
								(self changeState: 2)
							)
							(LSneeds500
								(Print 320 25)
								(Format @str 320 26)
								(self changeState: 2)
							)
							(LSWaiting4Divorce
								(Print 320 27)
								(Format @str 320 28)
								(self changeState: 2)
							)
							(LSdivorceReady
								(Print 320 23)
								(Format @str 320 24)
								(self changeState: 2)
							)
							(LSdone
								(Print 320 29)
								(Format @str 320 30)
								(self changeState: 2)
							)
						)
					)
				)
			)
			(
				(and
					(ego has: iSpaKeycard)
					(or
						(Said '/keycard,(card<key,club,membership)')
						(Said '//keycard,(card<key,club,membership)')
					)
				)
				(Print 320 31)
				(Format @str 320 32)
			)
			(
			(or (Said '/entertainer') (Said '//entertainer'))
				(User canInput: FALSE)
				(Print 320 33)
				(Format @str 320 34)
				(self changeState: 2)
			)
			(
				(or
					(Said '/attorney,attorney,attorney')
					(Said '//attorney,attorney,attorney')
				)
				(User canInput: FALSE)
				(Format @str 320 35)
				(self changeState: 2)
			)
			(
				(or
					(Said 'affirmative')
					(Said 'give,buy,use/buck,man,charge,500,bill,500')
					(Said 'give,buy,use//buck,man,charge,500,bill,500')
				)
				(cond 
					((not (ego has: iMoney))
						(Print 320 36)
					)
					((not (& (ego onControl:) cMAGENTA))
						(NotClose)
					)
					((and (!= currentStatus egoNORMAL) (!= currentStatus egoSHOWGIRL))
						(GoodIdea)
					)
					((!= local301 0)
						(Print 320 17)
					)
					((== lawyerState LSbusy)
						(User canInput: FALSE)
						(Format @str 320 37)
						(self changeState: 2)
					)
					((== lawyerState LSfree)
						(User canInput: FALSE)
						(Format @str 320 38)
						(self changeState: 2)
					)
					((> lawyerState LSneeds500)
						(User canInput: FALSE)
						(Format @str 320 39)
						(self changeState: 2)
					)
					((!= dollars 500)
						(Print 320 40)
						(Print 320 41 #at -1 144)
					)
					(else
						(Ok)
						(= lawyerState LSdivorce)
						(= dollars 0)
						(ego put: iMoney -1)
						(User canInput: FALSE)
						(Print 320 42 #icon 24 0 0)
						(Format @str 320 43)
						(RoomScript changeState: 2)
						(self changeState: 2)
					)
				)
			)
			(
				(and
					(ego has: iDivorceDecree)
					(not (ego has: iSpaKeycard))
					(Said 'look,look/decree,document,document')
				)
				(event claimed: FALSE)
				(return)
			)
			(
				(or
					(Said '/decree,document,(document<decree)')
					(Said '//decree,document,(document<decree)')
				)
				(cond 
					((not (& (ego onControl:) cMAGENTA))
						(NotClose)
					)
					((and (!= currentStatus egoNORMAL) (!= currentStatus egoSHOWGIRL))
						(GoodIdea)
					)
					((!= local301 0)
						(Print 320 17)
					)
					((== lawyerState LSbusy)
						(User canInput: FALSE)
						(Printf 320 44 introductoryPhrase)
						(Print 320 45)
						(Format @str 320 46)
						(self changeState: 2)
					)
					((== lawyerState LSfree)
						(User canInput: FALSE)
						(Printf 320 44 introductoryPhrase)
						(Print 320 47)
						(Format @str 320 48)
						(RoomScript changeState: 2)
						(self changeState: 2)
					)
					((== lawyerState LSwaiting4Deed)
						(User canInput: FALSE)
						(Print 320 49)
						(Format @str 320 50)
						(self changeState: 2)
					)
					((< lawyerState LSWaiting4Divorce)
						(User canInput: 0)
						(Print 320 51)
						(Format @str 320 52)
						(self changeState: 2)
					)
					((< lawyerState LSdivorceReady)
						(User canInput: FALSE)
						(Print 320 53)
						(Format @str 320 54)
						(self changeState: 2)
					)
					((> lawyerState LSdivorceReady)
						(User canInput: FALSE)
						(Print 320 55)
						(Format @str 320 56)
						(self changeState: 2)
					)
					(else
						(User canInput: FALSE)
						(Print 320 57)
						(Format @str 320 58
							(if (Btst fScrewedSuzi)
								{business doing pleasure}
							else
								{pleasure doing business}
							)
						)
						(= lawyerState LSdone)
						(ego get: iDivorceDecree)
						(theGame changeScore: 20)
						(self changeState: 2)
					)
				)
			)
			((and (ego has: iLandDeed) (Said 'look/deed,land'))
				(event claimed: FALSE)
				(return)
			)
			((or (Said '//deed,land') (Said '/deed,land'))
				(cond 
					((not (& (ego onControl:) cMAGENTA))
						(NotClose)
					)
					((and (!= currentStatus egoNORMAL) (!= currentStatus egoSHOWGIRL))
						(GoodIdea)
					)
					((!= local301 0)
						(Print 320 17)
					)
					((== lawyerState LSbusy)
						(User canInput: FALSE)
						(Printf 320 44 introductoryPhrase)
						(Print 320 59)
						(Format @str 320 60)
						(self changeState: 2)
					)
					((== lawyerState LSfree)
						(User canInput: FALSE)
						(Printf 320 44 introductoryPhrase)
						(Print 320 61)
						(Format @str 320 62)
						(RoomScript changeState: 2)
						(self changeState: 2)
					)
					((< lawyerState LSdeedReady)
						(User canInput: FALSE)
						(Print 320 21)
						(Format @str 320 22)
						(self changeState: 2)
					)
					((> lawyerState LSdeedReady)
						(User canInput: FALSE)
						(Print 320 63)
						(Format @str 320 64)
						(self changeState: 2)
					)
					(else
						(User canInput: FALSE)
						(Print 320 65)
						(Format @str 320 66)
						(= lawyerState LSneeds500)
						(ego get: iLandDeed)
						(theGame changeScore: 20)
						(self changeState: 2)
					)
				)
			)
		)
	)
)

(instance atpSign of PicView
	(properties
		y 125
		x 151
		view 320
		loop 1
		priority 9
		signal ignrAct
	)
)

(instance atpDCHSign of PicView
	(properties
		y 93
		x 151
		view 320
		priority 9
		signal ignrAct
	)
)

(instance atpDeskStuff of PicView
	(properties
		y 141
		x 165
		view 320
		loop 2
		priority 12
	)
)

(instance atpPencils of PicView
	(properties
		y 136
		x 105
		view 320
		loop 2
		cel 1
		priority 12
	)
)

(instance atpFax of PicView
	(properties
		y 142
		x 106
		view 320
		loop 4
		priority 12
		signal ignrAct
	)
)

(instance aFax of Extra
	(properties
		y 133
		x 106
		view 320
		loop 5
	)
	
	(method (init)
		(super init:)
		(self
			cycleType: ExtraEndLoop
			pauseCel: -2
			minPause: 99
			maxPause: 999
			setPri: 12
			isExtra: TRUE
			ignoreActors:
			startExtra:
		)
	)
)

(instance aRoger of Prop
	(properties
		y 130
		x 146
		view 321
		loop 3
	)
	
	(method (init)
		(super init:)
		(self setPri: 12 setScript: SecretaryScript)
	)
)

(instance aDoor of AutoDoor
	(properties
		y 39
		x 153
		view 320
		entranceTo 323
	)
	
	(method (init)
		(super init:)
		(self setLoop: 3 setPri: 5)
	)
)
