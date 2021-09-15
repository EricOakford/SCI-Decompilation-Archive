;;; Sierra Script 1.0 - (do not remove this comment)
(script# 61)
(include game.sh)
(use Main)
(use Intrface)
(use Extra)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm61 0
)

(instance rm61 of Room
	(properties
		picture 61
	)
	
	(method (init)
		(Load VIEW vEgo)
		(Load VIEW 661)
		(Load VIEW 603)
		(super init:)
		(NormalEgo)
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
				aView14
				aView15
				aView16
				aView17
			doit:
		)
		(aNewspaper
			setPri: 6
			pauseCel: 255
			minPause: 3
			maxPause: 8
			init:
		)
		(aHand
			setPri: 9
			pauseCel: 255
			minPause: 3
			maxPause: 8
			minCycles: 5
			maxCycles: 11
			init:
		)
		(aKnitting
			setPri: 5
			pauseCel: 255
			minPause: 3
			maxPause: 11
			minCycles: 15
			maxCycles: 31
			init:
		)
		(self setRegions: AIRPLANE setScript: rm61Script)
		(aCockDoor
			setPri: 5
			stopUpd:
			init:
		)
		(if (== prevRoomNum 62)
			(ego observeControl: cYELLOW loop: 1 posn: 300 101)
			(addToPics add: aMainDoor doit:)
		else
			(Load VIEW 23)
			(aStewardess
				stopUpd:
				init:
			)
			(ego loop: 3 posn: 42 143)
			(HandsOff)
			(= currentStatus egoBOARDPLANE)
			(rm61Script changeState: 1)
		)
		(ego init:)
	)
)

(instance rm61Script of Script
	(method (doit)
		(super doit:)
		(if (== state 17)
			(ShakeScreen 1 (Random 1 3))
		)
		(if (& (ego onControl:) cGREEN)
			(curRoom newRoom: 62)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= seconds 2)
			)
			(2
				(ego setMotion: MoveTo 53 117 self)
			)
			(3
				(aStewardess setCycle: Forward)
				(= seconds 3)
			)
			(4
				(aStewardess setCel: 0)
				(Print 61 7 #draw)
				(Print 61 8 #icon 23 0 0)
				(= seconds 3)
			)
			(5
				(aStewardess setCycle: Forward)
				(= seconds 3)
			)
			(6
				(aStewardess setCel: 0)
				(Print 61 9 #draw)
				(ego put: iAirlineTicket -1)
				(Print 61 10)
				(Print 61 11)
				(= seconds 3)
			)
			(7
				(aStewardess setCycle: Forward)
				(= seconds 3)
			)
			(8
				(aStewardess setCel: 0 stopUpd:)
				(Print 61 12 #draw)
				(Print 61 13)
				(Print 61 14)
				(Print (Format @str 61 15 tritePhrase))
				(= seconds 2)
			)
			(9
				(ego setMotion: MoveTo 103 102 self)
			)
			(10
				(= seconds 3)
			)
			(11
				(Print 61 16)
				(ego setMotion: MoveTo 326 102)
			)
			(12
				(HandsOff)
				(= currentStatus egoSTOPPED)
				(Ok)
				(aCockDoor setCycle: EndLoop self)
			)
			(13
				(ego illegalBits: 0 setMotion: MoveTo 0 102 self)
			)
			(14
				(aCockDoor setCycle: BegLoop self)
			)
			(15
				(Print 61 17)
				(= seconds 3)
			)
			(16
				(Print 61 18)
				(Print 61 19)
				(= seconds 3)
			)
			(17
				(= seconds 3)
			)
			(18
				(Print 61 20)
				(= currentStatus egoDYING)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/galley,curtain,bimbo')
				(if (ego inRect: 0 0 106 92)
					(Print 61 0)
				else
					(NotClose)
				)
			)
			(if (Said '/door')
				(Print 61 1)
			)
			(if (Said '[/children,man,bimbo,airline,airport]')
				(Print 61 2)
				(Print 61 3)
			)
		)
		(if (Said 'call/man,bimbo,children')
			(Print 61 4)
			(Print 61 5)
		)
		(if (Said 'open/door')
			(cond 
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				((& (ego onControl:) cRED)
					(Print 61 6)
				)
				((not (& (ego onControl:) cCYAN))
					(NotClose)
				)
				(else
					(self changeState: 12)
				)
			)
		)
	)
)

(instance aView1 of PicView
	(properties
		y 75
		x 254
		view 661
		cel 2
		priority 4
		signal ignrAct
	)
)

(instance aView2 of PicView
	(properties
		y 75
		x 205
		view 661
		cel 3
		priority 4
		signal ignrAct
	)
)

(instance aView3 of PicView
	(properties
		y 76
		x 151
		view 661
		cel 4
		priority 4
		signal ignrAct
	)
)

(instance aView4 of PicView
	(properties
		y 90
		x 206
		view 661
		cel 1
		priority 5
		signal ignrAct
	)
)

(instance aView5 of PicView
	(properties
		y 91
		x 261
		view 661
		cel 4
		priority 5
		signal ignrAct
	)
)

(instance aView6 of PicView
	(properties
		y 91
		x 150
		view 661
		priority 5
		signal ignrAct
	)
)

(instance aView7 of PicView
	(properties
		y 119
		x 157
		view 661
		loop 1
		cel 4
		priority 8
		signal ignrAct
	)
)

(instance aView8 of PicView
	(properties
		y 119
		x 286
		view 661
		loop 1
		cel 5
		priority 8
		signal ignrAct
	)
)

(instance aView9 of PicView
	(properties
		y 120
		x 223
		view 661
		loop 1
		cel 3
		priority 8
		signal ignrAct
	)
)

(instance aView10 of PicView
	(properties
		y 137
		x 152
		view 661
		loop 1
		priority 10
		signal ignrAct
	)
)

(instance aView11 of PicView
	(properties
		y 137
		x 288
		view 661
		loop 1
		cel 2
		priority 10
		signal ignrAct
	)
)

(instance aView12 of PicView
	(properties
		y 138
		x 226
		view 661
		loop 1
		cel 1
		priority 10
		signal ignrAct
	)
)

(instance aView13 of PicView
	(properties
		y 77
		x 189
		view 661
		loop 2
		cel 1
		priority 4
		signal ignrAct
	)
)

(instance aView14 of PicView
	(properties
		y 93
		x 239
		view 661
		loop 2
		cel 2
		priority 5
		signal ignrAct
	)
)

(instance aView15 of PicView
	(properties
		y 110
		x 188
		view 661
		loop 2
		priority 7
		signal ignrAct
	)
)

(instance aView16 of PicView
	(properties
		y 110
		x 255
		view 661
		loop 2
		cel 1
		priority 7
		signal ignrAct
	)
)

(instance aView17 of PicView
	(properties
		y 129
		x 279
		view 661
		loop 2
		cel 2
		priority 9
		signal ignrAct
	)
)

(instance aMainDoor of PicView
	(properties
		y 156
		x 38
		view 603
		loop 1
		priority 15
		signal ignrAct
	)
)

(instance aNewspaper of Extra
	(properties
		y 74
		x 146
		view 661
		loop 3
	)
)

(instance aHand of Extra
	(properties
		y 108
		x 279
		view 661
		loop 4
	)
)

(instance aKnitting of Extra
	(properties
		y 59
		x 149
		view 661
		loop 5
	)
)

(instance aCockDoor of Prop
	(properties
		y 103
		x 41
		view 603
		signal ignrAct
	)
)

(instance aStewardess of Prop
	(properties
		y 100
		x 55
		view 600
		loop 4
	)
)
