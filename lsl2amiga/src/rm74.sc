;;; Sierra Script 1.0 - (do not remove this comment)
(script# 74)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm74 0
)

(local
	vineInRoom
	triedToGoWest
	triedToClimbOut
	swingingOnVine
)
(instance rm74 of Rm
	(properties
		picture 74
		horizon 66
		east 75
		west 73
	)
	
	(method (init)
		(Load rsVIEW 178)
		(Load rsVIEW 729)
		(super init:)
		(if ((inventory at: 29) ownedBy: curRoomNum)
			(= vineInRoom 1)
			(aTHEvine stopUpd: init:)
		)
		(addToPics
			add: aVineA aVineB aVineC aVineD aVineE aVineF aVineG aVineH
			doit:
		)
		(aRapids setCycle: Fwd init:)
		(aVine1 setPri: 8 stopUpd: init:)
		(aVine2 setPri: 8 stopUpd: init:)
		(aVine3 setPri: 8 stopUpd: init:)
		(if
		(and (== passedPiranhaWater 0) (!= prevRoomNum 75))
			(Load rsVIEW 177)
			(Load rsVIEW 176)
			(Load rsVIEW 110)
			(aBigEgo init:)
			(aBigEgoFace setPri: 15 cycleSpeed: 1 init:)
		)
		(NormalEgo)
		(if (== prevRoomNum 75)
			(ego posn: 288 78 observeControl: 2 16)
		else
			(ego posn: 2 76)
		)
		(ego init:)
		(self setRegions: 700 setScript: rm74Script)
	)
)

(instance rm74Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
			(and (ego inRect: 261 72 321 77) (== currentStatus 0)) (curRoom newRoom: 75))
			(
				(and
					(== currentStatus 1016)
					(& (ego onControl:) $0010)
				)
				(self changeState: 3)
			)
			(
				(and
					(== currentStatus 1016)
					(& (ego onControl:) $0400)
				)
				(if (== triedToClimbOut 0)
					(= triedToClimbOut 1)
					(Print 74 0)
				)
			)
			(
				(and
					(== currentStatus 1016)
					(& (ego onControl:) $2000)
				)
				(if (== triedToGoWest 0)
					(= triedToGoWest 1)
					(Print 74 1)
				)
			)
			(
			(and (== currentStatus 0) (== (ego onControl:) 2)) (self changeState: 1))
			(else (= triedToGoWest 0) (= triedToClimbOut 0))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= currentStatus 1016)
				(User canControl: 1 canInput: 1)
				(ego
					view: 176
					observeControl: 1 -32768
					setLoop: -1
					setStep: 3 2
					setCycle: Walk
					setPri: -1
				)
				(= cycles 8)
			)
			(2 (Print 74 17 #draw))
			(3
				(Print 74 18)
				(ego
					view: 177
					setPri: -1
					illegalBits: -32768
					observeControl: 16 2 16384
				)
				(User canInput: 0)
				(= currentStatus 1000)
				(curRoom east: 0)
				(= cycles 0)
				(= seconds 6)
			)
			(4
				(User canControl: 0)
				(ego setMotion: 0 setLoop: 2)
				(aBigEgo posn: (ego x?) 87 stopUpd:)
				(aBigEgoFace posn: (ego x?) 87 cel: 0 setCycle: End self)
			)
			(5 (= cycles 7))
			(6
				(aBigEgoFace setCycle: Beg self)
			)
			(7
				(aBigEgoFace loop: 5 setCycle: End)
				(= seconds 3)
			)
			(8
				(Print 74 19 #at -1 20)
				(aBigEgoFace dispose:)
				(aBigEgo dispose:)
				(= seconds 3)
			)
			(9
				(Print 74 20)
				(Print 74 21)
				(= currentStatus 1001)
			)
			(10
				(Ok)
				(ego hide:)
				(User canControl: 0)
				(= currentStatus 1013)
				(aVine1 cel: 1 setCycle: CT 7 1 self)
			)
			(11
				(if swingingOnVine
					(= swingingOnVine 0)
					(self changeState: 14)
				else
					(aVine1 cel: 8 setCycle: End)
					(ego
						view: 178
						illegalBits: 0
						ignoreActors:
						ignoreHorizon:
						setLoop: 2
						setPri: 8
						setStep: 3 4
						cel: 0
						posn: 106 85
						show:
						setCycle: End
						setMotion: MoveTo 106 102 self
					)
				)
			)
			(12
				(ego setLoop: 4 posn: 106 103 cel: 0 setCycle: End self)
			)
			(13
				(Print 74 22 #at -1 20 #draw)
				(self changeState: 1)
			)
			(14
				(aVine1 setCel: 8 setCycle: End)
				(aVine2 setCel: 1 setCycle: CT 6 1 self)
			)
			(15
				(if swingingOnVine
					(= swingingOnVine 0)
					(self changeState: 18)
				else
					(aVine2 cel: 7 setCycle: End)
					(ego
						view: 178
						illegalBits: 0
						ignoreActors:
						ignoreHorizon:
						setLoop: 2
						setPri: 8
						setStep: 3 7
						cel: 0
						posn: 131 73
						show:
						setCycle: End
						setMotion: MoveTo 131 102 self
					)
				)
			)
			(16
				(ego setLoop: 4 posn: 131 103 cel: 0 setCycle: End self)
			)
			(17
				(Print 74 23 #at -1 20 #draw)
				(self changeState: 1)
			)
			(18
				(aVine1 stopUpd:)
				(aVine2 setCel: 7 setCycle: End)
				(aVine3 cel: 1 setCycle: CT 6 1 self)
			)
			(19
				(aVine2 stopUpd:)
				(if swingingOnVine
					(= swingingOnVine 0)
					(aVine3 setCel: 6)
					(self changeState: 23)
				else
					(aVine3 setCycle: Beg self)
				)
			)
			(20
				(ego
					view: 178
					illegalBits: 0
					ignoreActors:
					ignoreHorizon:
					setLoop: 2
					setPri: 8
					setStep: 3 7
					cel: 0
					posn: 131 73
					show:
					setCycle: End
					setMotion: MoveTo 131 102 self
				)
			)
			(21
				(ego setLoop: 4 posn: 131 103 cel: 0 setCycle: End self)
			)
			(22
				(Print 74 24 #at -1 20 #draw)
				(self changeState: 1)
			)
			(23
				(aVine3 setCel: 7 setCycle: End)
				(ego
					illegalBits: 0
					ignoreActors:
					ignoreHorizon:
					view: 178
					setLoop: 3
					setPri: 9
					setCel: 0
					posn: 158 61
					show:
					setCycle: CT 5 1
					setStep: 1 10
					setMotion: MoveTo 167 122 self
				)
			)
			(24
				(aVine3 stopUpd:)
				(ego setCycle: End self)
			)
			(25
				(NormalEgo 0)
				(if (== passedPiranhaWater 0)
					(= passedPiranhaWater 1)
					(theGame changeScore: 6)
					(Print 74 25 #at -1 130 #draw)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/lagoon,beach') (Print 74 2))
			(if (Said '/brook,fluid') (Print 74 3))
			(if (Said '/fish')
				(if (!= currentStatus 1016)
					(Print 74 4)
				else
					(Print 74 5)
				)
			)
			(if (Said '/palm,landscape,bush')
				(Print 74 6)
				(if (== vineInRoom 1) (Print 74 7))
			)
			(if (Said '/boulder,cascade') (Print 74 8))
			(if (Said '[/airport,forest]') (Print 74 9))
		)
		(if (Said 'jerk,get/landscape')
			(cond 
				((!= currentStatus 0) (NotNow))
				((not vineInRoom) (Print 74 10))
				((ego inRect: 59 91 78 98) (Print 74 11))
				((not (ego inRect: 149 100 195 129)) (NotClose))
				(else
					(= vineInRoom 0)
					(ego get: 29)
					(theGame changeScore: 4)
					(aTHEvine dispose:)
					(Print 74 12 #draw)
					(Print 74 13)
				)
			)
		)
		(if (Said 'bathing') (Print 74 14))
		(if (Said '*/boulder')
			(if (== currentStatus 1016)
				(Print 74 15)
			else
				(Print 74 16)
			)
		)
		(if
			(or
				(Said 'hop,(let<board)')
				(Said 'free,carry,apply,swing,alter/landscape')
				(Said 'swing/brook,fluid')
				(Said 'swing<on/landscape')
			)
			(cond 
				(
				(and (>= currentStatus 1013) (<= currentStatus 1015)) (= swingingOnVine 1))
				((!= currentStatus 0) (NotNow))
				((not (ego inRect: 59 91 78 98)) (NotClose))
				(else (self changeState: 10))
			)
		)
	)
)

(instance aTHEvine of View
	(properties
		y 37
		x 184
		view 178
		loop 5
		priority 9
		signal $4000
	)
)

(instance aVineA of PV
	(properties
		y 40
		x 18
		view 178
		loop 5
		cel 1
		priority 12
		signal $4000
	)
)

(instance aVineB of PV
	(properties
		y 55
		x 37
		view 178
		loop 5
		priority 12
		signal $4000
	)
)

(instance aVineC of PV
	(properties
		y 27
		x 223
		view 178
		loop 5
		priority 12
		signal $4000
	)
)

(instance aVineD of PV
	(properties
		y 10
		x 202
		view 178
		loop 5
		cel 1
		priority 12
		signal $4000
	)
)

(instance aVineE of PV
	(properties
		y 40
		x 44
		view 178
		loop 5
		cel 1
		priority 12
		signal $4000
	)
)

(instance aVineF of PV
	(properties
		y 32
		x 192
		view 178
		loop 5
		priority 12
		signal $4000
	)
)

(instance aVineG of PV
	(properties
		y 51
		x 23
		view 178
		loop 5
		priority 7
		signal $4000
	)
)

(instance aVineH of PV
	(properties
		y 42
		x 11
		view 178
		loop 1
		priority 7
		signal $4000
	)
)

(instance aBigEgo of View
	(properties
		y 1098
		x 243
		view 110
		priority 14
		signal $4000
	)
)

(instance aRapids of Prop
	(properties
		y 67
		x 124
		view 729
	)
)

(instance aVine1 of Prop
	(properties
		y 44
		x 85
		view 178
		signal $4000
	)
)

(instance aVine2 of Prop
	(properties
		y 32
		x 111
		view 178
		loop 1
		signal $4000
	)
)

(instance aVine3 of Prop
	(properties
		y 20
		x 136
		view 178
		loop 1
		signal $4000
	)
)

(instance aBigEgoFace of Prop
	(properties
		y 1098
		x 243
		view 177
		loop 4
		signal $4000
	)
)
