;;; Sierra Script 1.0 - (do not remove this comment)
(script# 116)
(include game.sh)
(use Main)
(use Intrface)
(use Timer)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm116 0
)

(local
	canBuySwimsuit
	stealMsg
	moneyOwed
)
(instance rm116 of Room
	(properties
		picture 116
		horizon 5
	)
	
	(method (init)
		(Load VIEW 225)
		(Load VIEW 226)
		(super init:)
		(addToPics
			add:
				aView1
				aView2
				aView3
				aView4
				aView5
				aView6
				aView7
				aView8
				aView9
				aView10
				aView11
				aView12
				aView13
			doit:
		)
		(if (ego has: iMillionDollarBill)
			(Load VIEW 4)
			(= canBuySwimsuit TRUE)
			(addToPics add: aView14 doit:)
		)
		(aBigClerk
			view: 226
			setLoop: 5
			posn: 273 1058
			setPri: 15
			setCycle: Forward
			init:
		)
		(aBigHand
			setPri: 15
			setCycle: Forward
			cycleSpeed: 2
			init:
		)
		(aClerk
			posn: 174 105
			setPri: 9
			setCycle: Walk
			init:
		)
		(NormalEgo)
		(ego posn: 161 161 init:)
		(self setRegions: CITY setScript: rm116Script)
	)
)

(instance rm116Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cBLUE)
			(curRoom newRoom: 16)
		)
		(cond 
			((not (& (ego onControl:) cGREEN))
				(= stealMsg FALSE)
			)
			((and moneyOwed (not stealMsg))
				(= stealMsg TRUE)
				(Print 116 0)
			)
		)
	)
	
	(method (changeState newState &tmp egoX)
		(switch (= state newState)
			(0
				(= seconds (Random 5 10))
			)
			(1
				(aClerk
					setLoop: 1
					setMotion: MoveTo 138 105 self
					setCycle: Walk
				)
			)
			(2
				(aClerk setCel: 0)
				(= seconds (Random 10 20))
			)
			(3
				(aClerk setLoop: 3 setCel: 0 setCycle: Forward cycleSpeed: 2)
				(if (> machineSpeed 30)
					(aBigHand setCel: 0 setCycle: Forward posn: 29 187)
				)
				(= seconds (Random 5 10))
			)
			(4
				(aClerk
					setLoop: 2
					setCel: 0
					setCycle: EndLoop self
					cycleSpeed: 0
				)
				(aBigHand posn: 29 1187)
			)
			(5
				(aClerk
					setLoop: 0
					cycleSpeed: 1
					setMotion: MoveTo 174 105 self
					setCycle: Walk
				)
			)
			(6
				(= state 0)
				(aClerk setCel: 0)
				(= seconds (Random 20 30))
			)
			(7
				(= canBuySwimsuit (= seconds 0))
				(HandsOff)
				(Print 116 31 #icon 4 0 0)
				(aBigHand hide:)
				(if (< (= egoX (ego x?)) 138) (= egoX 138))
				(if (> egoX 174) (= egoX 174))
				(aClerk
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo egoX 105 self
				)
			)
			(8
				(aClerk setLoop: 2 setCel: 2)
				(Print 116 32 #draw)
				(= seconds 3)
			)
			(9
				(Print 116 33)
				(ego put: iMillionDollarBill -1)
				(= seconds 3)
			)
			(10
				(Print 116 34)
				(= seconds 2)
			)
			(11
				(Print 116 35)
				(= seconds 2)
			)
			(12
				(Print 116 36)
				(= seconds 2)
			)
			(13
				(Print 116 37)
				(= seconds 2)
			)
			(14
				(Print 116 38)
				(= seconds 2)
			)
			(15
				(Print 116 39)
				(Print 116 40)
				(= seconds 2)
			)
			(16
				(Print (Format @str 116 41 tritePhrase))
				(Print 116 42)
				(= cycles 6)
			)
			(17
				(Print 116 43)
				(NormalEgo)
				(ego get: iWadODough)
				(= moneyOwed FALSE)
				(theGame changeScore: 3)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (and (== canBuySwimsuit TRUE) (Said '/brick,sign'))
				(Print 116 1)
			)
			(if (Said '/rack,bikini,(bra<bathing),job')
				(cond 
					((not (ego inRect: 137 88 182 96))
						(Print 116 2)
					)
					((ego has: iSwimsuit)
						(Print 116 3)
					)
					((!= canBuySwimsuit TRUE)
						(Print 116 4)
					)
					(else
						(Print 116 5)
					)
				)
			)
			(if (Said '/bra')
				(Print 116 6)
				(Print 116 7)
			)
			(if (Said '/art,ceiling')
				(Print 116 8)
				(Print 116 9 #at -1 130)
			)
			(if (Said '/finger')
				(Print 116 10)
			)
			(if (Said '/brick')
				(Print 116 11)
				(Print 116 12)
			)
			(if (Said '/art')
				(Print 116 13)
			)
			(if (Said '/buffet,finger,bimbo,agent')
				(aBigClerk posn: 159 58 stopUpd:)
				(Print 116 14 #draw)
				(Timer setReal: aBigClerk 5)
				(HandsOff)
			)
			(if (Said '[/airport,building,building,look,ceiling,carpet]')
				(if (ego has: iSwimsuit)
					(Print 116 15)
				else
					(Print 116 16)
					(if (== canBuySwimsuit TRUE)
						(Print 116 17)
					)
				)
			)
		)
		(if (Said 'get/bikini,(bra<bathing),job')
			(cond 
				((not (ego inRect: 137 88 182 96))
					(Print 116 18)
				)
				((ego has: iSwimsuit)
					(Print 116 19)
				)
				((!= canBuySwimsuit TRUE)
					(Print 116 20)
				)
				(else
					(Print 116 21)
					(= moneyOwed TRUE)
					(ego get: iSwimsuit)
					(theGame changeScore: 5)
					(ego observeControl: cBLUE)
				)
			)
		)
		(if
			(or
				(Said 'give,finger/agent,children,bimbo/buck')
				(Said 'give,finger/buck')
				(Said 'buy')
			)
			(cond 
				((not moneyOwed)
					(Print 116 22)
				)
				((not (ego inRect: 120 130 192 140))
					(Print 116 23)
				)
				(else
					(self changeState: 7)
				)
			)
		)
		(if (Said 'get/bra')
			(Print 116 24)
		)
		(if (Said 'call/bimbo,children,agent')
			(if (or (ego has: iMillionDollarBill) (ego has: iWadODough))
				(Print 116 25)
				(Print 116 26)
				(Print 116 27)
				(Print 116 28 #at -1 130)
			else
				(Print 116 29)
				(Print 116 30)
			)
		)
	)
)

(instance aBigClerk of Prop
	(method (cue)
		(Print 116 44)
		(Print 116 45 #at 55 155 #width 210)
		(self posn: 273 1058)
		(HandsOn)
	)
)

(instance aView1 of PicView
	(properties
		y 89
		x 160
		view 225
		priority 5
		signal ignrAct
	)
)

(instance aView2 of PicView
	(properties
		y 150
		x 238
		view 225
		loop 1
		priority 11
		signal ignrAct
	)
)

(instance aView3 of PicView
	(properties
		y 85
		x 72
		view 225
		loop 1
		cel 1
		priority 5
		signal ignrAct
	)
)

(instance aView4 of PicView
	(properties
		y 118
		x 85
		view 225
		loop 2
		priority 8
		signal ignrAct
	)
)

(instance aView5 of PicView
	(properties
		y 109
		x 97
		view 225
		loop 2
		priority 7
		signal ignrAct
	)
)

(instance aView6 of PicView
	(properties
		y 129
		x 74
		view 225
		loop 2
		cel 1
		priority 9
		signal ignrAct
	)
)

(instance aView7 of PicView
	(properties
		y 138
		x 62
		view 225
		loop 2
		cel 1
		priority 10
		signal ignrAct
	)
)

(instance aView8 of PicView
	(properties
		y 134
		x 257
		view 225
		loop 2
		cel 2
		priority 9
		signal ignrAct
	)
)

(instance aView9 of PicView
	(properties
		y 131
		x 250
		view 225
		loop 2
		cel 2
		priority 9
		signal ignrAct
	)
)

(instance aView10 of PicView
	(properties
		y 124
		x 239
		view 225
		loop 2
		cel 2
		priority 8
		signal ignrAct
	)
)

(instance aView11 of PicView
	(properties
		y 139
		x 264
		view 225
		loop 2
		cel 2
		priority 10
		signal ignrAct
	)
)

(instance aView12 of PicView
	(properties
		y 108
		x 216
		view 225
		loop 2
		cel 3
		priority 6
		signal ignrAct
	)
)

(instance aView13 of PicView
	(properties
		y 115
		x 228
		view 225
		loop 2
		cel 3
		priority 7
		signal ignrAct
	)
)

(instance aView14 of PicView
	(properties
		y 65
		x 157
		view 225
		cel 1
	)
)

(instance aBigHand of Prop
	(properties
		y 1187
		x 29
		view 226
		loop 4
		signal ignrAct
	)
)

(instance aClerk of Actor
	(properties
		view 226
		loop 3
		signal ignrAct
	)
)
