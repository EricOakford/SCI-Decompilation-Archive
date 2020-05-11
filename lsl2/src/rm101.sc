;;; Sierra Script 1.0 - (do not remove this comment)
(script# 101)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm101 0
)

(local
	receptionist
	guy
	doll
	westDoor
	eastDoor
	northDoor
	myNum1
	myNum2
	myNum3
	myNum4
	myNum5
	myNum6
	winningNum1
	winningNum2
	winningNum3
	winningNum4
	winningNum5
	winningNum6
)
(instance rm101 of Room
	(properties
		picture 101
		south 11
	)
	
	(method (init)
		(Load VIEW 201)
		(Load VIEW 203)
		(Load VIEW 204)
		(Load VIEW 205)
		(if (ego has: iLotteryTicket) (Load VIEW 2))
		(super init:)
		((View new:)
			view: 201
			loop: 0
			cel: 0
			posn: 51 71
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 201
			loop: 0
			cel: 1
			posn: 266 71
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 203
			loop: 6
			cel: 0
			setPri: 10
			posn: 168 119
			ignoreActors:
			addToPic:
		)
		(aBigClerk
			view: 203
			setLoop: 7
			posn: 223 1042
			setPri: 14
			init:
		)
		(aBigMouth
			view: 203
			posn: 223 1042
			setLoop: 9
			setPri: 15
			setCycle: Forward
			cycleSpeed: 3
			init:
		)
		((= guy (Actor new:))
			view: 204
			posn: 33 1118
			illegalBits: 0
			setCycle: Walk
			init:
			setScript: guyScript
		)
		((= doll (Actor new:))
			view: 205
			posn: 288 1118
			illegalBits: 0
			setCycle: Walk
			init:
			setScript: dollScript
		)
		((= receptionist (Prop new:))
			view: 203
			setLoop: 0
			posn: 158 118
			cycleSpeed: 1
			setPri: 9
			ignoreActors:
			init:
			setScript: receptionistScript
		)
		(NormalEgo)
		((= westDoor (Prop new:))
			view: 201
			ignoreActors:
			setLoop: 1
			posn: 58 106
			setPri: 7
			stopUpd:
			init:
		)
		((= eastDoor (Prop new:))
			view: 201
			ignoreActors:
			setLoop: 2
			posn: 259 106
			setPri: 7
			stopUpd:
			init:
		)
		((= northDoor (Prop new:))
			view: 201
			ignoreActors:
			setLoop: 3
			posn: 145 79
			setPri: 4
			stopUpd:
			init:
		)
		(self setScript: rm101Script)
		(if (== prevRoomNum 104)
			(ego posn: 258 121 loop: 1 init:)
		else
			(ego posn: 156 180 init:)
		)
	)
)

(instance rm101Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			((& (ego onControl:) $0008) (curRoom newRoom: 11))
			(
				(and
					(ego inRect: 214 80 229 87)
					(not (guyScript state?))
				)
				(guyScript changeState: 1)
			)
			(
				(and
					(ego inRect: 91 80 109 87)
					(not (dollScript state?))
				)
				(dollScript changeState: 1)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(receptionistScript changeState: 14)
				(Print 101 15 #icon 2 0 0)
				(= seconds 4)
			)
			(2
				(= winningNum1 (Random 100 999))
				(= winningNum2 (Random 100 999))
				(= winningNum3 (Random 100 999))
				(= winningNum4 (Random 100 999))
				(= winningNum5 (Random 100 999))
				(= winningNum6 (Random 100 999))
				(Print
					(Format @str 101 16
						winningNum1 winningNum2 winningNum3 winningNum4 winningNum5
						winningNum6
					)
				)
				(= str 0)
				(= myNum1 0)
				(while (or (< myNum1 100) (> myNum1 999))
					(= myNum1 (GetNumber {Pick #1}))
				)
				(= myNum2 0)
				(while (or (< myNum2 100) (> myNum2 999))
					(= myNum2 (GetNumber {Pick #2}))
				)
				(= myNum3 0)
				(while (or (< myNum3 100) (> myNum3 999))
					(= myNum3 (GetNumber {Pick #3}))
				)
				(= myNum4 0)
				(while (or (< myNum4 100) (> myNum4 999))
					(= myNum4 (GetNumber {Pick #4}))
				)
				(= myNum5 0)
				(while (or (< myNum5 100) (> myNum5 999))
					(= myNum5 (GetNumber {Pick #5}))
				)
				(= myNum6 0)
				(while (or (< myNum6 100) (> myNum6 999))
					(= myNum6 (GetNumber {Pick #6}))
				)
				(= seconds 3)
			)
			(3
				(if
					(or
						(!= winningNum1 myNum1)
						(!= winningNum2 myNum2)
						(!= winningNum3 myNum3)
						(!= winningNum4 myNum4)
						(!= winningNum5 myNum5)
						(!= winningNum6 myNum6)
					)
					(self changeState: 10)
				else
					(theGame changeScore: 10)
					(Print 101 17)
					(Print 101 18)
					(= seconds 2)
					(ego put: iLotteryTicket -1)
				)
			)
			(4
				(ego setMotion: MoveTo 228 146 self)
			)
			(5
				(Print 101 19)
				(Print 101 20)
				(ego setMotion: MoveTo 228 117 self)
			)
			(6
				(ego setMotion: MoveTo 162 82 self)
			)
			(7
				(northDoor setCycle: EndLoop self)
			)
			(8
				(ego illegalBits: 0 setMotion: MoveTo 162 77 self)
			)
			(9 (curRoom newRoom: 102))
			(10
				(= seconds (= cycles 0))
				(Print 101 21)
				(Print 101 22)
				(User canControl: TRUE canInput: TRUE)
				(receptionistScript changeState: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'give,finger,look/ticket')
			(cond 
				((ego has: iCruiseTicket) (event claimed: FALSE) (return))
				((not (ego has: iLotteryTicket)) (PrintDontHaveIt))
				((not (ego inRect: 134 144 185 152)) (PrintNotCloseEnough))
				(else (self changeState: 1))
			)
		)
		(if (Said 'look>')
			(if (Said '/door') (Print 101 0))
			(if (Said '/burn') (Print 101 1))
			(if (Said '/brick,sign')
				(Print 101 2)
				(Print 101 3)
				(Print 101 4)
				(Print 101 5)
			)
			(if (Said '/art') (Print 101 6))
			(if (Said '/cup') (Print 101 7))
			(if (Said '/bimbo,skylight,buffet')
				(aBigClerk posn: 223 41 stopUpd:)
				(aBigMouth posn: 223 41)
				(receptionistScript changeState: 5)
				(Timer setReal: aBigClerk 5)
				(Timer setReal: aBigMouth 5)
				(HandsOff)
			)
			(if (Said '[/airport,krod,lobby,building,brick]')
				(Print 101 8)
			)
		)
		(if (Said 'open/door') (Print 101 9))
		(if (Said '/reporter') (Print 101 10))
		(if (Said 'call,ask/bimbo')
			(Print (Format @str 101 11 introductoryPhrase))
			(Print 101 12)
		)
		(if (Said 'ok')
			(Print 101 13)
			(Print 101 14 #at -1 152)
		)
	)
)

(instance receptionistScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 0)
				(= seconds (Random 3 7))
			)
			(1
				(receptionist setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(2
				(receptionist setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(3
				(receptionist cel: 0 setCycle: EndLoop self)
			)
			(4
				(receptionist setLoop: 4 setCel: 255 setCycle: BegLoop self)
			)
			(5
				(= seconds (= cycles 0))
				(receptionist setLoop: 0)
				(if (> (Random 1 3) 1)
					(self changeState: 0)
				else
					(= seconds (Random 3 7))
				)
			)
			(6
				(receptionist setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(7
				(receptionist setLoop: 3 setCycle: Forward)
				(= seconds (Random 2 4))
			)
			(8
				(receptionist setCel: 0)
				(= seconds (Random 3 7))
			)
			(9
				(receptionist setLoop: 3 setCycle: Forward)
				(= seconds (Random 2 4))
			)
			(10
				(receptionist setCel: 0)
				(= seconds (Random 3 7))
			)
			(11
				(receptionist setLoop: 3 setCycle: Forward)
				(= seconds (Random 1 3))
			)
			(12
				(receptionist setLoop: 2 setCel: 255 setCycle: BegLoop self)
			)
			(13 (self changeState: 0))
			(14
				(= cycles 0)
				(receptionist setLoop: 1 setCycle: Forward)
				(= seconds (Random 2 4))
			)
			(15 (receptionist setLoop: 0))
		)
	)
)

(instance guyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(westDoor setCycle: EndLoop self)
			)
			(2
				(guy posn: 33 117 setMotion: MoveTo 72 118 self)
			)
			(3
				(guy setMotion: MoveTo 132 182 self)
				(westDoor setCycle: BegLoop self)
			)
			(4 (westDoor stopUpd:))
			(5
				(guy setMotion: MoveTo 132 232 self)
			)
			(6
				(guy dispose:)
				(Print 101 23)
			)
		)
	)
)

(instance dollScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(eastDoor setCycle: EndLoop self)
			)
			(2
				(doll posn: 288 117 setMotion: MoveTo 258 118 self)
			)
			(3
				(doll setMotion: MoveTo 162 182 self)
				(eastDoor setCycle: BegLoop self)
			)
			(4 (eastDoor stopUpd:))
			(5
				(doll setMotion: MoveTo 162 232)
			)
			(6
				(doll dispose:)
				(Print 101 23)
			)
		)
	)
)

(instance aBigClerk of Prop
	(properties)
	
	(method (cue)
		(Print 101 24)
		(Print 101 25 #at 55 155 #width 210)
		(self posn: 223 1042)
	)
)

(instance aBigMouth of Prop
	(properties)
	
	(method (cue)
		(self posn: 223 1042)
		(HandsOn)
	)
)
