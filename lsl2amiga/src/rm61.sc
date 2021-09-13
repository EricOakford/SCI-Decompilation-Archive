;;; Sierra Script 1.0 - (do not remove this comment)
(script# 61)
(include sci.sh)
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

(instance rm61 of Rm
	(properties
		picture 61
	)
	
	(method (init)
		(Load rsVIEW 100)
		(Load rsVIEW 661)
		(Load rsVIEW 603)
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
		(self setRegions: 600 setScript: rm61Script)
		(aCockDoor setPri: 5 stopUpd: init:)
		(if (== prevRoomNum 62)
			(ego observeControl: 16384 loop: 1 posn: 300 101)
			(addToPics add: aMainDoor doit:)
		else
			(Load rsVIEW 23)
			(aStewardess stopUpd: init:)
			(ego loop: 3 posn: 42 143)
			(HandsOff)
			(= currentStatus 9)
			(rm61Script changeState: 1)
		)
		(ego init:)
	)
)

(instance rm61Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== state 17) (ShakeScreen 1 (Random 1 3)))
		(if (& (ego onControl:) $0004) (curRoom newRoom: 62))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (= seconds 2))
			(2
				(ego setMotion: MoveTo 53 117 self)
			)
			(3
				(aStewardess setCycle: Fwd)
				(= seconds 3)
			)
			(4
				(aStewardess setCel: 0)
				(Print 61 7 #draw)
				(Print 61 8 #icon 23 0 0)
				(= seconds 3)
			)
			(5
				(aStewardess setCycle: Fwd)
				(= seconds 3)
			)
			(6
				(aStewardess setCel: 0)
				(Print 61 9 #draw)
				(ego put: 23 -1)
				(Print 61 10)
				(Print 61 11)
				(= seconds 3)
			)
			(7
				(aStewardess setCycle: Fwd)
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
			(10 (= seconds 3))
			(11
				(Print 61 16)
				(ego setMotion: MoveTo 326 102)
			)
			(12
				(HandsOff)
				(= currentStatus 1000)
				(Ok)
				(aCockDoor setCycle: End self)
			)
			(13
				(ego illegalBits: 0 setMotion: MoveTo 0 102 self)
			)
			(14
				(aCockDoor setCycle: Beg self)
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
			(17 (= seconds 3))
			(18
				(Print 61 20)
				(= currentStatus 1001)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
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
			(if (Said '/door') (Print 61 1))
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
				((!= currentStatus 0) (NotNow))
				((& (ego onControl:) $0010) (Print 61 6))
				((not (& (ego onControl:) $0008)) (NotClose))
				(else (self changeState: 12))
			)
		)
	)
)

(instance aView1 of PV
	(properties
		y 75
		x 254
		view 661
		cel 2
		priority 4
		signal $4000
	)
)

(instance aView2 of PV
	(properties
		y 75
		x 205
		view 661
		cel 3
		priority 4
		signal $4000
	)
)

(instance aView3 of PV
	(properties
		y 76
		x 151
		view 661
		cel 4
		priority 4
		signal $4000
	)
)

(instance aView4 of PV
	(properties
		y 90
		x 206
		view 661
		cel 1
		priority 5
		signal $4000
	)
)

(instance aView5 of PV
	(properties
		y 91
		x 261
		view 661
		cel 4
		priority 5
		signal $4000
	)
)

(instance aView6 of PV
	(properties
		y 91
		x 150
		view 661
		priority 5
		signal $4000
	)
)

(instance aView7 of PV
	(properties
		y 119
		x 157
		view 661
		loop 1
		cel 4
		priority 8
		signal $4000
	)
)

(instance aView8 of PV
	(properties
		y 119
		x 286
		view 661
		loop 1
		cel 5
		priority 8
		signal $4000
	)
)

(instance aView9 of PV
	(properties
		y 120
		x 223
		view 661
		loop 1
		cel 3
		priority 8
		signal $4000
	)
)

(instance aView10 of PV
	(properties
		y 137
		x 152
		view 661
		loop 1
		priority 10
		signal $4000
	)
)

(instance aView11 of PV
	(properties
		y 137
		x 288
		view 661
		loop 1
		cel 2
		priority 10
		signal $4000
	)
)

(instance aView12 of PV
	(properties
		y 138
		x 226
		view 661
		loop 1
		cel 1
		priority 10
		signal $4000
	)
)

(instance aView13 of PV
	(properties
		y 77
		x 189
		view 661
		loop 2
		cel 1
		priority 4
		signal $4000
	)
)

(instance aView14 of PV
	(properties
		y 93
		x 239
		view 661
		loop 2
		cel 2
		priority 5
		signal $4000
	)
)

(instance aView15 of PV
	(properties
		y 110
		x 188
		view 661
		loop 2
		priority 7
		signal $4000
	)
)

(instance aView16 of PV
	(properties
		y 110
		x 255
		view 661
		loop 2
		cel 1
		priority 7
		signal $4000
	)
)

(instance aView17 of PV
	(properties
		y 129
		x 279
		view 661
		loop 2
		cel 2
		priority 9
		signal $4000
	)
)

(instance aMainDoor of PV
	(properties
		y 156
		x 38
		view 603
		loop 1
		priority 15
		signal $4000
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
		signal $4000
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
