;;; Sierra Script 1.0 - (do not remove this comment)
(script# 101)
(include game.sh)
(use Main)
(use Intrface)
(use Timer)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm101 0
)

(local
	inputNum1
	inputNum2
	inputNum3
	inputNum4
	inputNum5
	inputNum6
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
		(if (ego has: iLotteryTicket)
			(Load VIEW 2)
		)
		(super init:)
		(addToPics add: aView1 aView2 aView3 doit:)
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
		(aGuy
			illegalBits: 0
			setCycle: Walk
			init:
			setScript: guyScript
		)
		(aDoll
			illegalBits: 0
			setCycle: Walk
			init:
			setScript: dollScript
		)
		(aReceptionist
			cycleSpeed: 1
			setPri: 9
			ignoreActors:
			init:
			setScript: receptionistScript
		)
		(NormalEgo)
		(aDoorWest stopUpd: init:)
		(aDoorEast stopUpd: init:)
		(aDoorNorth stopUpd: init:)
		(self setScript: rm101Script)
		(if (== prevRoomNum 104)
			(ego posn: 258 121 loop: 1 init:)
		else
			(ego posn: 156 180 init:)
		)
	)
)

(instance rm101Script of Script
	(method (doit)
		(super doit:)
		(cond 
			((& (ego onControl:) cCYAN)
				(curRoom newRoom: 11)
			)
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
				(Print 101 18 #icon 2 0 0)
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
					(Format @str 101 19
						winningNum1 winningNum2 winningNum3
						winningNum4 winningNum5 winningNum6
					)
				)
				(= str 0)
				(= inputNum1 0)
				(while (or (< inputNum1 100) (> inputNum1 999))
					(= inputNum1 (GetNumber {Pick #1}))
				)
				(= inputNum2 0)
				(while (or (< inputNum2 100) (> inputNum2 999))
					(= inputNum2 (GetNumber {Pick #2}))
				)
				(= inputNum3 0)
				(while (or (< inputNum3 100) (> inputNum3 999))
					(= inputNum3 (GetNumber {Pick #3}))
				)
				(= inputNum4 0)
				(while (or (< inputNum4 100) (> inputNum4 999))
					(= inputNum4 (GetNumber {Pick #4}))
				)
				(= inputNum5 0)
				(while (or (< inputNum5 100) (> inputNum5 999))
					(= inputNum5 (GetNumber {Pick #5}))
				)
				(= inputNum6 0)
				(while (or (< inputNum6 100) (> inputNum6 999))
					(= inputNum6 (GetNumber {Pick #6}))
				)
				(= seconds 3)
			)
			(3
				(if
					(or
						(!= winningNum1 inputNum1)
						(!= winningNum2 inputNum2)
						(!= winningNum3 inputNum3)
						(!= winningNum4 inputNum4)
						(!= winningNum5 inputNum5)
						(!= winningNum6 inputNum6)
					)
					(self changeState: 10)
				else
					(theGame changeScore: 10)
					(Print 101 20)
					(Print 101 21)
					(= seconds 2)
					(ego put: iLotteryTicket -1)
				)
			)
			(4
				(ego setMotion: MoveTo 228 146 self)
			)
			(5
				(Print 101 22)
				(Print 101 23)
				(ego setMotion: MoveTo 228 117 self)
			)
			(6
				(ego setMotion: MoveTo 162 82 self)
			)
			(7
				(aDoorNorth setCycle: EndLoop self)
			)
			(8
				(ego illegalBits: 0 setMotion: MoveTo 162 77 self)
			)
			(9
				(curRoom newRoom: 102)
			)
			(10
				(= seconds (= cycles 0))
				(Print 101 24)
				(Print 101 25)
				(User canControl: TRUE canInput: TRUE)
				(receptionistScript changeState: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'give,finger,look/ticket')
			(cond 
				((ego has: iCruiseTicket)
					(event claimed: FALSE)
					(return)
				)
				((not (ego has: iLotteryTicket))
					(DontHave)
				)
				((not (ego inRect: 134 144 185 152))
					(NotClose)
				)
				(else
					(self changeState: 1)
				)
			)
		)
		(if (Said 'look>')
			(if (Said '/door')
				(Print 101 0)
			)
			(if (Said '/burn')
				(Print 101 1)
			)
			(if (Said '/brick,sign')
				(Print 101 2)
				(Print 101 3)
				(Print 101 4)
				(Print 101 5)
			)
			(if (Said '/art')
				(Print 101 6)
			)
			(if (Said '/cup')
				(Print 101 7)
			)
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
		(if (Said 'open/door')
			(Print 101 9)
		)
		(if (Said '/reporter')
			(Print 101 10)
		)
		(if (Said 'call,ask/bimbo')
			;Amiga addition
			(if (or (ego has: iMillionDollarBill) (ego has: iWadODough))
				(Print 101 11)
				(Print 101 12)
				(Print 101 13 #at -1 130)
			else
				(Print (Format @str 101 14 introductoryPhrase))
				(Print 101 15)
			)
		)
		(if (Said 'ok')
			(Print 101 16)
			(Print 101 17 #at -1 130)
		)
	)
)

(instance receptionistScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 0)
				(= seconds (Random 3 7))
			)
			(1
				(aReceptionist setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(2
				(aReceptionist setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(3
				(aReceptionist cel: 0 setCycle: EndLoop self)
			)
			(4
				(aReceptionist setLoop: 4 setCel: 255 setCycle: BegLoop self)
			)
			(5
				(= seconds (= cycles 0))
				(aReceptionist setLoop: 0)
				(if (> (Random 1 3) 1)
					(self changeState: 0)
				else
					(= seconds (Random 3 7))
				)
			)
			(6
				(aReceptionist setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(7
				(aReceptionist setLoop: 3 setCycle: Forward)
				(= seconds (Random 2 4))
			)
			(8
				(aReceptionist setCel: 0)
				(= seconds (Random 3 7))
			)
			(9
				(aReceptionist setLoop: 3 setCycle: Forward)
				(= seconds (Random 2 4))
			)
			(10
				(aReceptionist setCel: 0)
				(= seconds (Random 3 7))
			)
			(11
				(aReceptionist setLoop: 3 setCycle: Forward)
				(= seconds (Random 1 3))
			)
			(12
				(aReceptionist setLoop: 2 setCel: 255 setCycle: BegLoop self)
			)
			(13
				(self changeState: 0)
			)
			(14
				(= cycles 0)
				(aReceptionist setLoop: 1 setCycle: Forward)
				(= seconds (Random 2 4))
			)
			(15
				(aReceptionist setLoop: 0)
			)
		)
	)
)

(instance guyScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(aDoorWest setCycle: EndLoop self)
			)
			(2
				(aGuy posn: 33 117 setMotion: MoveTo 72 118 self)
			)
			(3
				(aGuy setMotion: MoveTo 132 182 self)
				(aDoorWest setCycle: BegLoop self)
			)
			(4
				(aDoorWest stopUpd:)
			)
			(5
				(aGuy setMotion: MoveTo 132 232 self)
			)
			(6
				(aGuy dispose:)
				(Print 101 26)
			)
		)
	)
)

(instance dollScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(aDoorEast setCycle: EndLoop self)
			)
			(2
				(aDoll posn: 288 117 setMotion: MoveTo 258 118 self)
			)
			(3
				(aDoll setMotion: MoveTo 162 182 self)
				(aDoorEast setCycle: BegLoop self)
			)
			(4
				(aDoorEast stopUpd:)
			)
			(5
				(aDoll setMotion: MoveTo 162 232)
			)
			(6
				(aDoll dispose:)
				(Print 101 26)
			)
		)
	)
)

(instance aBigClerk of Prop
	(method (cue)
		(Print 101 27)
		(Print 101 28 #at 55 155 #width 210)
		(self posn: 223 1042)
	)
)

(instance aBigMouth of Prop
	(method (cue)
		(self posn: 223 1042)
		(HandsOn)
	)
)

(instance aView1 of PicView
	(properties
		y 71
		x 51
		view 201
		priority 10
		signal ignrAct
	)
)

(instance aView2 of PicView
	(properties
		y 71
		x 266
		view 201
		cel 1
		priority 10
		signal ignrAct
	)
)

(instance aView3 of PicView
	(properties
		y 119
		x 168
		view 203
		loop 6
		priority 10
		signal ignrAct
	)
)

(instance aGuy of Actor
	(properties
		y 1118
		x 33
		view 204
	)
)

(instance aDoll of Actor
	(properties
		y 1118
		x 288
		view 205
	)
)

(instance aReceptionist of Prop
	(properties
		y 118
		x 158
		view 203
	)
)

(instance aDoorWest of Prop
	(properties
		y 106
		x 58
		view 201
		loop 1
		priority 7
		signal ignrAct
	)
)

(instance aDoorEast of Prop
	(properties
		y 106
		x 259
		view 201
		loop 2
		priority 7
		signal ignrAct
	)
)

(instance aDoorNorth of Prop
	(properties
		y 79
		x 145
		view 201
		loop 3
		priority 4
		signal ignrAct
	)
)
