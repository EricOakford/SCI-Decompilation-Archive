;;; Sierra Script 1.0 - (do not remove this comment)
(script# 35)
(include sci.sh)
(use Main)
(use Intrface)
(use Extra)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm35 0
)

(local
	local0
	spinachDipInRoom
	henchwomanAppeared
	canFollowHenchwoman
)
(instance rm35 of Rm
	(properties
		picture 35
		horizon 5
		south 31
	)
	
	(method (init)
		(Load rsVIEW 315)
		(Load rsVIEW 319)
		(super init:)
		(self setRegions: 300 setScript: rm35Script)
		(if ((inventory at: 13) ownedBy: curRoomNum)
			(= spinachDipInRoom 1)
			(aSpinachDip stopUpd: init:)
		else
			(self setRegions: 8)
			(= henchView 316)
			(Load rsVIEW henchView)
			(aHench
				view: henchView
				illegalBits: -32768
				setCycle: Walk
				init:
				setScript: henchScript
			)
		)
		(addToPics
			add:
				aGirl1
				aGirl2
				aMan1
				aMan2
				aBoat
				aGirl3
				aMan3
				aStool
				aBar
				aBarGirl1
				aBarGirl2
			doit:
		)
		(aBartender stopUpd: setPri: 3 init:)
		(aTV setPri: 3 setCycle: Fwd isExtra: 1 init:)
		(aGirl1drinking
			setPri: 7
			minPause: 20
			maxPause: 40
			minCycles: 5
			maxCycles: 10
			init:
		)
		(aTitFeeler
			setPri: 6
			minPause: 20
			maxPause: 30
			minCycles: 5
			maxCycles: 8
			init:
		)
		(aGirl2drinking
			setPri: 7
			minPause: 22
			maxPause: 50
			minCycles: 7
			maxCycles: 12
			init:
		)
		(aGirl3drinking setPri: 11 init:)
		(aManDrinking
			setPri: 11
			minPause: 32
			maxPause: 70
			minCycles: 5
			maxCycles: 10
			init:
		)
		(aShip
			setLoop: 6
			setPri: 0
			illegalBits: 0
			setStep: 1 1
			moveSpeed: 1
			init:
			setScript: shipScript
		)
		(NormalEgo)
		(ego posn: 157 157 init:)
	)
)

(instance rm35Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0002)
			(if (== henchwomanAppeared 0)
				(curRoom newRoom: 31)
			else
				(Print 35 0)
				(Print 35 1)
				(= currentStatus 23)
				(curRoom newRoom: 95)
			)
		)
		(if
			(and
				henchwomanIsHere
				canFollowHenchwoman
				(> (ego y?) 146)
			)
			(= canFollowHenchwoman 0)
			(= currentStatus 23)
			(= henchwomanAppeared 1)
			(curRoom south: 95)
			(Print 35 2)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= currentStatus 1)
				(HandsOff)
				(Ok)
				(ego setMotion: MoveTo 166 107 self)
			)
			(2
				(ego
					view: 104
					ignoreActors:
					illegalBits: 0
					setMotion: 0
					posn: 163 95
					setLoop: 0
					setPri: 6
					cel: 0
					setCycle: End self
				)
			)
			(3
				(= currentStatus 1009)
				(if
					(and
						(== spinachDipInRoom 0)
						(not (henchScript state?))
					)
					(henchScript changeState: 1)
				)
				(User canInput: 1)
			)
			(4
				(Ok)
				(= currentStatus 1)
				(HandsOff)
				(ego
					setLoop: 0
					setMotion: 0
					setCel: 255
					setCycle: Beg self
				)
			)
			(5
				(ego posn: 164 106)
				(NormalEgo 3)
			)
			(6
				(= currentStatus 1000)
				(HandsOff)
				(henchScript changeState: 255)
				(Print 35 33)
				(= seconds 3)
			)
			(7
				(Print 35 34)
				(Print 35 35)
				(Print 35 36)
				(aBartender setLoop: 0 setCycle: Fwd)
				(= seconds 5)
			)
			(8
				(aBartender
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(9 (= seconds 2))
			(10
				(aBartender
					setLoop: 2
					cel: 0
					cycleSpeed: 0
					setCycle: End self
				)
				(ego setLoop: 1 cycleSpeed: 0 cel: 0 setCycle: End)
			)
			(11
				(aBartender stopUpd:)
				(Print 35 37)
				(ego setLoop: 2 cel: 0 setCycle: End)
				(= seconds 3)
			)
			(12
				(ego cycleSpeed: 1 setLoop: 3 cel: 0 setCycle: End self)
			)
			(13
				(ego cycleSpeed: 0 setLoop: 4 cel: 0 setCycle: Fwd)
				(= seconds 5)
			)
			(14
				(Print 35 38)
				(= seconds 3)
			)
			(15
				(Print 35 39)
				(= currentStatus 23)
				(curRoom newRoom: 96)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if
			(and
				henchwomanIsHere
				(< (henchScript state?) 5)
				(Said '/bimbo>')
			)
			(cond 
				((Said 'call/') (Print (Format @str 35 3 introductoryPhrase)))
				((Said 'look/') (Print 35 4))
				(else (Print 35 5) (Print 35 6 #at -1 130))
			)
		)
		(if (Said 'look>')
			(if (and henchwomanIsHere (Said '/bimbo'))
				(if (< (henchScript state?) 3)
					(Print 35 7)
				else
					(Print 35 8)
				)
			)
			(if (Said '/agent') (Print 35 9))
			(if (Said '/buffet,man,bimbo,children')
				(Print 35 10)
				(Print 35 11 #at -1 130)
			)
			(if (Said '/cup,craft') (Print 35 12) (Print 35 13))
			(if (Said '/burn') (Print 35 14))
			(if (Said '/computer,krod') (Print 35 15))
			(if (Said '/bottle,bar')
				(if (== currentStatus 1009)
					(Print 35 16)
				else
					(Print 35 17)
				)
				(if spinachDipInRoom (Print 35 18))
			)
			(if (and spinachDipInRoom (Said '/bowl,bread'))
				(Print 35 19)
			)
			(if (Said '[/airport]')
				(Print 35 20)
				(if spinachDipInRoom (Print 35 18))
			)
		)
		(if (Said 'bath[/down,barstool]')
			(cond 
				((== currentStatus 1009) (YouAre))
				((!= currentStatus 0) (NotNow))
				(
					(and
						(not (ego inRect: 148 103 222 109))
						(not (ego inRect: 64 107 285 128))
					)
					(Print 35 21)
				)
				(else (self changeState: 1))
			)
		)
		(if
			(or
				(Said 'new,(get<up),new[/up]')
				(Said 'disembark[/barstool]')
			)
			(cond 
				((== currentStatus 0) (Print 35 22))
				((!= currentStatus 1009) (NotNow))
				(else (self changeState: 4))
			)
		)
		(if (Said 'call/agent')
			(cond 
				((== currentStatus 0) (Print 35 22))
				((!= currentStatus 1009) (NotNow))
				(else
					(Print (Format @str 35 23 introductoryPhrase))
					(Print 35 24)
					(Print 35 25 #at -1 130)
				)
			)
		)
		(if (Said 'call/bimbo')
			(cond 
				((== currentStatus 0) (Print 35 22))
				((!= currentStatus 1009) (NotNow))
				(else
					(Print (Format @str 35 26 introductoryPhrase))
					(Print 35 27)
					(if (> filthLevel 10) (Print 35 28))
				)
			)
		)
		(if (Said 'call') (Print 35 29))
		(if (or (Said 'give/i/beer') (Said 'buy/beer'))
			(cond 
				((== currentStatus 0) (Print 35 22))
				((!= currentStatus 1009) (NotNow))
				(else (Print 35 30))
			)
		)
		(if (or (Said 'give/i/drink') (Said 'buy/drink'))
			(cond 
				((== currentStatus 0) (Print 35 22))
				((!= currentStatus 1009) (NotNow))
				(else (self changeState: 6))
			)
		)
		(if (and spinachDipInRoom (Said 'eat/bread'))
			(cond 
				((!= currentStatus 0) (NotNow))
				((not (ego inRect: 73 100 106 104)) (NotClose))
				(else
					((inventory at: 13) moveTo: -1)
					(aSpinachDip dispose:)
					(= spinachDipInRoom 0)
					(theGame changeScore: -5)
					(Print 35 31 #at 15 -1 #width 280 #draw)
				)
			)
		)
		(if (Said 'get/bread')
			(cond 
				((== spinachDipInRoom 0) (AlreadyTook))
				((!= currentStatus 0) (NotNow))
				((not (ego inRect: 73 100 106 104)) (NotClose))
				(else
					(Print 35 32)
					(ego get: 13)
					(theGame changeScore: 2)
					(aSpinachDip dispose:)
					(= spinachDipInRoom 0)
				)
			)
		)
	)
)

(instance shipScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aShip
					setCel: (if (aShip cel?) 0 else 1)
					posn: -30 63
					setMotion: MoveTo 333 64 self
				)
			)
			(1
				(= cycles (Random 30 50))
				(= state -1)
			)
		)
	)
)

(instance henchScript of Script
	(properties)
	
	(method (changeState newState)
		(if (== currentStatus 1000) (return))
		(switch (= state newState)
			(1 (= cycles (Random 50 100)))
			(2
				(if (!= currentStatus 1009)
					(-- state)
					(= cycles (Random 50 100))
				else
					(aHench setMotion: MoveTo 157 107 self)
					(NotifyScript 8 1)
					(= henchwomanIsHere 1)
				)
			)
			(3
				(aHench loop: 0)
				(Print 35 40 #draw #at -1 20)
				(Print 35 41 #at -1 130)
				(= seconds 5)
			)
			(4
				(Print 35 42)
				(aHench setMotion: MoveTo 155 234 self)
				(= canFollowHenchwoman 1)
			)
			(5 (= seconds 10))
			(6
				(aHench dispose:)
				(= henchView 0)
				(= henchwomanIsHere 0)
				(= canFollowHenchwoman 0)
			)
		)
	)
)

(instance aSpinachDip of View
	(properties
		y 79
		x 94
		view 315
		cel 2
		priority 4
		signal $4000
	)
)

(instance aGirl1 of PV
	(properties
		y 144
		x 252
		view 315
		cel 5
		priority 11
		signal $4000
	)
)

(instance aGirl2 of PV
	(properties
		y 123
		x 319
		view 315
		cel 5
		priority 11
		signal $4000
	)
)

(instance aMan1 of PV
	(properties
		y 147
		x 201
		view 315
		cel 6
		priority 11
		signal $4000
	)
)

(instance aMan2 of PV
	(properties
		y 137
		x 277
		view 315
		cel 6
		priority 11
		signal $4000
	)
)

(instance aBoat of PV
	(properties
		y 62
		x -7
		view 315
		loop 6
		priority 0
		signal $4000
	)
)

(instance aGirl3 of PV
	(properties
		y 127
		view 315
		loop 4
		priority 11
		signal $4000
	)
)

(instance aMan3 of PV
	(properties
		y 132
		x 39
		view 315
		loop 5
		priority 11
		signal $4000
	)
)

(instance aStool of PV
	(properties
		y 104
		x 165
		view 315
		cel 1
		priority 6
		signal $4000
	)
)

(instance aBar of PV
	(properties
		y 79
		x 165
		view 315
		priority 2
		signal $4000
	)
)

(instance aBarGirl1 of PV
	(properties
		y 103
		x 200
		view 315
		cel 3
		priority 6
		signal $4000
	)
)

(instance aBarGirl2 of PV
	(properties
		y 101
		x 227
		view 315
		cel 4
		priority 6
		signal $4000
	)
)

(instance aHench of Act
	(properties
		y 233
		x 155
	)
)

(instance aBartender of Prop
	(properties
		y 68
		x 183
		view 319
		signal $4000
	)
)

(instance aTV of Prop
	(properties
		y 42
		x 162
		view 315
		loop 7
		signal $4000
	)
)

(instance aGirl1drinking of Extra
	(properties
		y 100
		x 313
		view 315
		loop 1
	)
)

(instance aTitFeeler of Extra
	(properties
		y 104
		x 121
		view 315
		loop 2
	)
)

(instance aGirl2drinking of Extra
	(properties
		y 88
		x 19
		view 315
		loop 3
	)
)

(instance aGirl3drinking of Extra
	(properties
		y 144
		x 66
		view 315
		loop 4
	)
)

(instance aManDrinking of Extra
	(properties
		y 147
		x 113
		view 315
		loop 5
	)
)

(instance aShip of Act
	(properties
		y 998
		x 999
		view 315
		signal $4000
	)
)
