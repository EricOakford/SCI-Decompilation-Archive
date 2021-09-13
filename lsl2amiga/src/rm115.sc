;;; Sierra Script 1.0 - (do not remove this comment)
(script# 115)
(include game.sh)
(use Main)
(use Intrface)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm115 0
)

(local
	local0
)
(instance rm115 of Room
	(properties
		picture 115
		horizon 5
		south 15
	)
	
	(method (init)
		(Load VIEW 221)
		(Load VIEW 222)
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
				aView14
				aView15
				aView16
				aView17
				aView18
				aView19
				aView20
				aView21
				aView22
				aView23
			doit:
		)
		(aBell
			setCycle: Forward
			init:
			setScript: bellScript
		)
		(aBigClerk
			view: 222
			loop: 1
			posn: 273 1058
			setPri: 15
			setCycle: Forward
			init:
		)
		(aClerk
			setPri: 14
			illegalBits: 0
			stopUpd:
			init:
		)
		(self setRegions: CITY setScript: rm115Script)
		(if (== currentStatus egoDOPPLEGANGER)
			(NormalEgo loopE)
			(= currentStatus egoDOPPLEGANGER)
			(HandsOff)
			(rm115Script changeState: 13)
		else
			(NormalEgo loopE)
			(Load SOUND 105)
			(theSound init:)
		)
		(ego posn: 64 115 init:)
	)
)

(instance rm115Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cBLUE)
			(curRoom newRoom: 15)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(Print (Format @str 115 15 introductoryPhrase))
				(aClerk setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(2
				(aClerk setLoop: 0 setCel: 0)
				(Print 115 16 #at -1 20 #font userFont #dispose)
				(Print 115 17 #at -1 130 #font smallFont)
				(cls)
				(= seconds 3)
			)
			(3
				(Print 115 18)
				(Print 115 19)
				(Print 115 20)
				(= seconds 3)
			)
			(4
				(if (> filthLevel 4)
					(Print 115 21)
				else
					(Print 115 22)
				)
				(Print 115 23)
				(Print 115 24 #at -1 20 #font userFont #dispose)
				(Print 115 25 #at -1 130 #font smallFont)
				(cls)
				(aClerk setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(5
				(aClerk setLoop: 0 setCel: 0)
				(Print 115 26)
				(Print 115 27 #at -1 20 #font userFont #dispose)
				(Print 115 28 #at -1 130 #font smallFont)
				(cls)
				(= seconds 3)
			)
			(6
				(Print 115 29)
				(Print 115 30)
				(Print 115 31 #at -1 20 #font userFont #dispose)
				(Print 115 32 #at -1 130 #font smallFont)
				(cls)
				(aClerk setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(7
				(aClerk setLoop: 0 setCel: 0)
				(Print 115 33 #at -1 20 #font userFont #dispose)
				(Print 115 34 #at -1 130 #font smallFont)
				(cls)
				(Print 115 35 #at -1 20 #font userFont #dispose)
				(Print 115 36 #at 20 110 #width 270 #font smallFont)
				(cls)
				(aClerk
					setLoop: 3
					setCel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(8
				(aClerk setLoop: 0 cycleSpeed: 0 setCel: 0)
				(aOnklunk
					setPri: 14
					stopUpd:
					init:
				)
				(= seconds 3)
			)
			(9
				(Print 115 37 #at -1 20 #font userFont #dispose)
				(Print 115 38 #at -1 130 #font smallFont)
				(cls)
				(= seconds 3)
			)
			(10
				(Print 115 39)
				(ego get: iOnklunk)
				(theGame changeScore: 7)
				(aOnklunk dispose:)
				(= seconds 3)
			)
			(11
				(Print 115 40 #at -1 20 #font userFont #dispose)
				(Print
					(Format @str 115 41 tritePhrase)
					#at -1 130
					#font smallFont
				)
				(cls)
				(ego setMotion: MoveTo 220 166 self)
			)
			(12
				(ego setMotion: MoveTo 222 182)
			)
			(13
				(ego setMotion: MoveTo 73 141 self)
				(= cycles 6)
			)
			(14
				(Print 115 42)
			)
			(15
				(ego setLoop: loopW)
				(Print 115 43 #draw)
				(Print 115 44)
				(Print 115 45)
				(theSound play:)
				(Print 115 46)
				(Print 115 47)
				(= seconds 3)
			)
			(16
				(Print 115 48)
				(= seconds 3)
			)
			(17
				(Print 115 49)
				(Print 115 50)
				(Print 115 51)
				(= seconds 3)
			)
			(18
				(Print 115 52)
				(Print 115 53)
				(= seconds 2)
			)
			(19
				(ego setLoop: -1 setMotion: MoveTo 220 166 self)
			)
			(20
				(ego loop: loopS setMotion: 0)
				(Print 115 54 #draw)
				(Print 115 55)
				(Print 115 56)
				(SetRegionTimer rgCITY 4 30)
				(curRoom newRoom: 21)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(cond 
				((Said '/amp')
					(Print 115 0)
				)
				((Said '/buffet')
					(Print 115 1)
				)
				((Said '/carpet')
					(Print 115 2)
				)
				((Said '/door')
					(Print 115 3)
					(Print 115 4)
				)
				((Said '/carpet')
					(Print 115 5)
				)
				((Said '/agent,children,bimbo')
					(aBigClerk posn: 273 58 stopUpd:)
					(Timer setReal: aBigClerk 5)
					(HandsOff)
				)
				((Said '[/airport,building,rack,rack,new,music]')
					(Print 115 6)
					(Print 115 7 #at -1 130)
				)
			)
		)
		(if (Said 'apply,get,play/amp')
			(Print 115 8)
		)
		(if (Said 'buy')
			(if (ego has: iMillionDollarBill)
				(Print 115 9)
				(Print 115 10)
			else
				(Print 115 11)
				(Print 115 10)
			)
		)
		(if (Said 'open/door')
			(Print 115 3)
			(Print 115 4)
		)
		(if (Said 'call/bimbo,children,agent')
			(cond 
				((not (ego inRect: 61 139 76 157))
					(NotClose)
				)
				((not ((inventory at: iOnklunk) ownedBy: curRoomNum))
					(AlreadyTook)
				)
				((== gotHaircutInCity FALSE)
					(Print (Format @str 115 12 introductoryPhrase))
					(Print 115 13)
					(Print 115 14 #at -1 130)
				)
				(else
					(self changeState: 1)
				)
			)
		)
	)
)

(instance bellScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 5)
			)
			(1
				(aBell cycleSpeed: 1)
				(= cycles 7)
			)
			(2
				(aBell cycleSpeed: 2)
				(= cycles 9)
			)
			(3
				(aBell cycleSpeed: 3)
				(= cycles 11)
			)
			(4
				(aBell setCel: 2 stopUpd:)
			)
		)
	)
)

(instance aBigClerk of Prop
	(method (cue)
		(Print 115 57)
		(Print 115 58 #at 55 155 #width 210)
		(self posn: 273 1058)
		(HandsOn)
	)
)

(instance theSound of Sound
	(properties
		number 105
	)
)

(instance aView1 of PicView
	(properties
		y 63
		x 225
		view 221
		loop 2
		cel 3
	)
)

(instance aView2 of PicView
	(properties
		y 87
		x 267
		view 221
		loop 2
		cel 4
	)
)

(instance aView3 of PicView
	(properties
		y 45
		x 149
		view 221
		loop 2
		cel 5
	)
)

(instance aView4 of PicView
	(properties
		y 99
		x 102
		view 221
		loop 3
		cel 4
	)
)

(instance aView5 of PicView
	(properties
		y 96
		x 120
		view 221
		loop 3
		cel 4
	)
)

(instance aView6 of PicView
	(properties
		y 100
		x 134
		view 221
		loop 3
		cel 3
	)
)

(instance aView7 of PicView
	(properties
		y 100
		x 145
		view 221
		loop 3
		cel 3
	)
)

(instance aView8 of PicView
	(properties
		y 101
		x 162
		view 221
		loop 2
	)
)

(instance aView9 of PicView
	(properties
		y 101
		x 186
		view 221
		loop 2
	)
)

(instance aView10 of PicView
	(properties
		y 109
		x 156
		view 221
		loop 2
	)
)

(instance aView11 of PicView
	(properties
		y 109
		x 191
		view 221
		loop 2
	)
)

(instance aView12 of PicView
	(properties
		y 101
		x 90
		view 221
		loop 2
	)
)

(instance aView13 of PicView
	(properties
		y 151
		x 69
		view 221
		loop 3
		priority 15
		signal ignrAct
	)
)

(instance aView14 of PicView
	(properties
		y 103
		x 23
		view 221
		loop 3
		cel 2
	)
)

(instance aView15 of PicView
	(properties
		y 113
		view 221
		loop 3
		cel 2
	)
)

(instance aView16 of PicView
	(properties
		y 79
		x 31
		view 221
		loop 3
		cel 1
	)
)

(instance aView17 of PicView
	(properties
		y 86
		x 41
		view 221
		loop 3
		cel 1
	)
)

(instance aView18 of PicView
	(properties
		y 77
		x 172
		view 221
		loop 2
		cel 1
		priority 6
	)
)

(instance aView19 of PicView
	(properties
		y 104
		x 89
		view 221
		loop 2
		cel 2
		priority 7
	)
)

(instance aView20 of PicView
	(properties
		y 135
		x 209
		view 221
		loop 3
		cel 5
		signal ignrAct
	)
)

(instance aView21 of PicView
	(properties
		y 70
		x 140
		view 221
		loop 3
		cel 7
	)
)

(instance aView22 of PicView
	(properties
		y 118
		x 42
		view 221
		loop 3
		cel 6
		priority 13
		signal ignrAct
	)
)

(instance aView23 of PicView
	(properties
		y 105
		x 67
		view 221
		priority 7
		signal ignrAct
	)
)

(instance aBell of Prop
	(properties
		y 62
		x 63
		view 221
		loop 1
	)
)

(instance aClerk of Actor
	(properties
		y 152
		x 41
		view 222
		signal ignrAct
	)
)

(instance aOnklunk of View
	(properties
		y 132
		x 51
		view 222
		loop 4
		signal ignrAct
	)
)
