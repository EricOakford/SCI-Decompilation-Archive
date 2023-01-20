;;; Sierra Script 1.0 - (do not remove this comment)
(script# 57)
(include sci.sh)
(use Main)
(use Intrface)
(use Deltaur)
(use RangeOsc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use DPath)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm57 0
	helmet 1
)

(local
	[pts1 60] = [319 172 287 169 257 153 306 138 271 124 256 129 223 134 178 115 134 128 117 115 166 96 142 85 135 88 137 93 130 96 114 94 107 97 129 108 85 121 56 110 52 118 40 116 27 120 148 184 173 184 220 168 258 189 0 189 0 0 319]
	[pts2 56] = [319 172 287 169 257 153 306 138 271 124 256 129 226 135 153 107 158 93 142 85 135 88 137 93 130 96 114 94 107 97 129 108 85 121 56 110 52 118 40 116 27 120 148 184 173 184 220 168 258 189 0 189 0 0 319]
	[pts3 50] = [319 172 287 169 257 153 306 138 271 124 256 129 223 134 178 115 134 128 117 115 165 107 161 92 142 85 135 88 137 93 52 118 40 116 27 120 148 184 173 184 220 168 258 189 0 189 0 0 319]
	[pts4 46] = [319 172 287 169 257 153 306 138 271 124 256 129 226 135 153 107 158 93 142 85 135 88 137 93 62 116 52 118 40 116 27 120 148 184 173 184 220 168 258 189 0 189 0 0 319]
	local212
	gEgoCel
)
(instance rm57 of Rm
	(properties
		lookStr {This room is similar to the storage room, with a large machine installed in one bulkhead.}
		picture 57
		east 66
	)
	
	(method (init)
		(Bset 62)
		(self setRegions: 703)
		(HandsOff)
		(= currentFloor 2)
		(LoadMany 128 37 36 157 417 85 418 39 1 61 2)
		(LoadMany 132 403 514 513 506 508 528 529 536 538)
		(sarien init:)
		(vent init:)
		(washerDoor init:)
		(switch prevRoomNum
			(55
				(ego view: 37 loop: 1 posn: 111 118)
			)
			(54
				(Bset 29)
				(Bclr 34)
				(Bset 35)
				(Load rsSOUND 403)
				(trunk init: hide: ignoreActors: 1 stopUpd:)
				(ego posn: (trunk x?) (trunk y?))
			)
			(else  (ego posn: 270 177))
		)
		(super init:)
		(if (!= (theMusic number?) 538)
			(theMusic number: 538 loop: -1 play:)
		)
		(features
			add: ventShaft shadows floor2 floor3 floor4 washerFeature
			eachElementDo: #init
			doit:
		)
		(ego init: hide:)
		(if (Btst 29)
			(openPoly points: @pts1 size: 31)
			(closedPoly points: @pts2 size: 28)
		else
			(openPoly points: @pts3 size: 25)
			(closedPoly points: @pts4 size: 23)
		)
		(if (== (DeltaurRegion egoStatus?) 0)
			(LoadMany 128 479 417 48)
		)
		(if (!= (DeltaurRegion egoStatus?) 0)
			(scraps init:)
		else
			(helmet
				init:
				setScript: killEgoTimeOut
				setPri: (- (washerDoor priority?) 1)
			)
		)
		(if (Btst 69)
			(self addObstacle: openPoly)
		else
			(self addObstacle: closedPoly)
		)
		(switch prevRoomNum
			(55
				(ego show:)
				(curRoom setScript: climbOutOfVent)
			)
			(54
				(curRoom setScript: egoClimbOutTrunk)
			)
			(else 
				(ego show:)
				(curRoom setScript: walkInRoom)
			)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			(local212 (self setScript: (ScriptID 157 0)))
			((& (ego onControl: 0) $0002) (self setScript: walkOutRoom))
		)
		(super doit:)
	)
	
	(method (newRoom n)
		(theMusic fade:)
		(super newRoom: n)
	)
)

(instance openPoly of Polygon
	(properties
		type $0002
	)
)

(instance closedPoly of Polygon
	(properties
		type $0002
	)
)

(instance trunk of Prop
	(properties
		x 104
		y 115
		lookStr {On the floor sits the trunk you rode here in.}
		view 154
		loop 1
		cycleSpeed 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 57 0))
			(2 (Print 57 1))
			(11 (Print 57 2))
			(12 (Print 57 2))
			(4 (Print 57 2))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance vent of Prop
	(properties
		x 92
		y 49
		lookStr {This is the protruding terminus of the vent shaft by which you entered.}
		view 154
		cycleSpeed 6
	)
	
	(method (init)
		(super init: &rest)
		(if (Btst 37) (self setCel: (self lastCel:)))
		(self stopUpd:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
				(if (Btst 37) (Print 57 3) else (Print 57 4))
			)
			(4
				(if (== theItem 4)
					(cond 
						((!= (DeltaurRegion egoStatus?) 0) (Print 57 5))
						((not (Btst 29)) (curRoom setScript: cantReachIt))
						((not (not (vent cel?))) (Print 57 6))
						(else (curRoom setScript: egoTestVent))
					)
				else
					(super doVerb: theVerb theItem)
				)
			)
			(3
				(cond 
					((!= (DeltaurRegion egoStatus?) 0) (Print 57 5))
					((not (Btst 29)) (curRoom setScript: cantReachIt))
					((not (vent cel?)) (curRoom setScript: egoTestVent))
					(else (Print 57 6))
				)
			)
			(11 (Print 57 7))
			(12 (Print 57 8))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance washerDoor of Prop
	(properties
		x 176
		y 82
		lookStr {Behind the glass door of the machine, you can see a helmet that has been carelessly left inside after the dry cycle.}
		view 157
		loop 1
		priority 8
		signal $4010
		cycleSpeed 6
	)
	
	(method (init)
		(super init: &rest)
		(if (Btst 69)
			(washerDoor setCel: (washerDoor lastCel:))
		)
		(washerDoor stopUpd:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
				(if (== (DeltaurRegion egoStatus?) 0)
					(Print 57 9)
				else
					(Print 57 10)
				)
			)
			(3
				(cond 
					((not (Btst 69)) (curRoom setScript: openWasherDoor))
					((== (DeltaurRegion egoStatus?) 0) (curRoom setScript: getInWasher))
					(else (Print 57 11))
				)
			)
			(11 (Print 57 12))
			(12 (Print 57 13))
			(4 (Print 57 14))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance scraps of View
	(properties
		x 191
		y 118
		nsTop 100
		nsBottom 135
		approachX 180
		approachY 119
		view 157
		loop 2
		signal $4000
	)
	
	(method (init)
		(= cel (if (ego has: 19) 1 else 0))
		(super init: &rest)
		(self approachVerbs: 3 2 11 12)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (scraps cel?)
					(Print 57 15)
				else
					(curRoom setScript: getId)
				)
			)
			(2
				(if (scraps cel?) (Print 57 16) else (Print 57 17))
			)
			(11 (Print 57 18))
			(12 (Print 57 19))
			(4 (Print 57 20))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sarien of Actor
	(properties
		x 278
		y 181
		view 417
		loop 1
		cycleSpeed 3
		moveSpeed 3
	)
	
	(method (init)
		(super init: &rest)
		(self stopUpd: hide: ignoreActors: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 57 21))
			(2
				(if (== (sarien view?) 418)
					(Print 57 22)
				else
					(Print 57 23)
				)
			)
			(11 (Print 57 21))
			(12 (Print 57 21))
			(4 (Print 57 21))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance helmet of Actor
	(properties
		x 210
		y 94
		lookStr {helmet}
		view 157
		loop 3
		signal $4000
		cycleSpeed 6
		moveSpeed 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(cond 
					((not (Btst 69)) (curRoom setScript: openWasherDoor))
					((== (DeltaurRegion egoStatus?) 0) (curRoom setScript: getInWasher))
					(else (Print 57 24))
				)
			)
			(2 (Print 57 25))
			(11 (Print 57 26))
			(12 (Print 57 27))
			(4 (Print 57 28))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ventShaft of RegionFeature
	(properties
		description {vent shaft}
		onMeCheck $2000
		lookStr {A large vent shaft protrudes from the left wall of the laundry room.}
		level 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 57 29))
			(2 (Print 57 30))
			(11 (Print 57 31))
			(12 (Print 57 31))
			(4 (Print 57 31))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance shadows of RegionFeature
	(properties
		description {shadows}
		onMeCheck $1000
		lookStr {Various pipes, ducts and other innocuous pieces of equipment form pronounced shadows throughout the room.}
		level 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 57 32))
			(2 (Print 57 33))
			(11 (Print 57 32))
			(12 (Print 57 32))
			(4 (Print 57 32))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance floor2 of RegionFeature
	(properties
		description {storage room}
		onMeCheck $0010
		lookStr {This room is similar to the storage room, with a large machine installed in one bulkhead.}
		level 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 57 34))
			(2 (Print 57 35))
			(11 (Print 57 36))
			(12 (Print 57 37))
			(4 (Print 57 38))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance floor3 of RegionFeature
	(properties
		description {storage room}
		onMeCheck $0020
		lookStr {This room is similar to the storage room, with a large machine installed in one bulkhead.}
		level 2
	)
	
	(method (doVerb theVerb)
		(floor2 doVerb: theVerb)
	)
)

(instance floor4 of RegionFeature
	(properties
		description {storage room}
		onMeCheck $0040
		lookStr {This room is similar to the storage room, with a large machine installed in one bulkhead.}
		level 2
	)
	
	(method (doVerb theVerb)
		(floor2 doVerb: theVerb)
	)
)

(instance washerFeature of RegionFeature
	(properties
		description {washing machine}
		onMeCheck $4000
		lookStr {This is almost certainly a cheap Sarien knock-off of the genuine Cleanse-O-Matic Rinse 'n Dip used to launder the crew uniforms aboard the Arcada.}
		level 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(cond 
					((not (Btst 69)) (curRoom setScript: openWasherDoor))
					((== (DeltaurRegion egoStatus?) 0) (curRoom setScript: getInWasher))
					(else (Print 57 24))
				)
			)
			(2 (Print 57 39))
			(11 (Print 57 40))
			(12 (Print 57 41))
			(4 (Print 57 42))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egoClimbOutTrunk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(trunk
					startUpd:
					show:
					view: 36
					loop: 1
					x: 103
					cycleSpeed: 6
					cel: 12
				)
				(= ticks 18)
			)
			(1
				(soundFx number: 513 loop: 1 play:)
				(trunk setCycle: Beg self)
			)
			(2
				(trunk
					loop: 0
					x: (- (trunk x?) 1)
					cel: 4
					setCycle: Beg self
				)
			)
			(3
				(trunk x: 104 loop: 5 cel: 2 setCycle: Beg self)
			)
			(4
				(soundFx number: 514 loop: 1 play:)
				(trunk view: 154 loop: 1 stopUpd: cel: 0)
				(vent stopUpd:)
				(ego show:)
				(EgoStatusCheck)
				(ego setHeading: 0 self)
				(Print 57 43)
			)
			(5
				(soundFx number: 536 loop: 1 flags: 1 play:)
				(ego setHeading: 180 self)
			)
			(6 (ego setHeading: 0 self))
			(7 (ego setHeading: 180 self))
			(8
				(ego
					view: 32
					setLoop: 2
					cel: 0
					setCycle: Fwd
					setMotion: MoveTo 110 126 self
				)
			)
			(9
				(EgoStatusCheck)
				(ego setHeading: 180 self)
			)
			(10 (ego setHeading: 0 self))
			(11 (ego setHeading: 90 self))
			(12
				(ego
					view: 32
					setLoop: 0
					cel: 0
					setCycle: Fwd
					setMotion: MoveTo 146 130 self
				)
			)
			(13
				(EgoStatusCheck)
				(ego setHeading: 180 self)
			)
			(14 (ego setHeading: 0 self))
			(15 (ego setHeading: 270 self))
			(16
				(ego
					view: 32
					setLoop: 1
					cel: 0
					setCycle: Fwd
					setMotion: MoveTo 134 148 self
				)
			)
			(17
				(ego
					view: 32
					cycleSpeed: 18
					setLoop: 4
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(18
				(soundFx number: 403 loop: 1 play:)
				(ego setCycle: End self)
			)
			(19
				(Print 57 44)
				(ego
					view: 32
					setLoop: 5
					posn: 112 158
					cel: 0
					setCycle: End self
				)
			)
			(20
				(ego view: 32 setLoop: 6 cel: 0 setCycle: End self)
			)
			(21
				(soundFx fade: flags: 0)
				(theMusic number: 538 loop: -1 play: 30 fade: 127 25 10 0)
				(EgoStatusCheck)
				(ego loop: 4)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getId of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(ego setHeading: 90 self)
			)
			(1
				(ego
					view: 85
					loop: 0
					cel: 0
					get: 19
					setCycle: CT 4 1 self
				)
			)
			(2
				(scraps setCel: 1)
				(SolvePuzzle 2 175)
				(= ticks 18)
			)
			(3 (ego setCycle: End self))
			(4
				(EgoStatusCheck)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getInWasher of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if register
			(if (and (> register 1) (not (helmet mover?)))
				(helmet
					setMotion: DPath 185 94 201 76 223 103 202 106 189 71 181 92 210 81 223 99
				)
			)
			(if (!= gEgoCel (ego cel?))
				(= gEgoCel (ego cel?))
				(soundFx number: 506 loop: 1 play: 127)
			)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= local212 0)
				(helmet setScript: 0)
				(HandsOff)
				(= register 0)
				(ego setMotion: PolyPath 185 127 self)
			)
			(1
				(ego setMotion: MoveTo 191 118 self)
			)
			(2
				(ego view: 39 loop: 1 cel: 0 setCycle: CT 7 1 self)
			)
			(3
				(ego setPri: 6 setCycle: End self)
				(helmet setPri: 6)
			)
			(4
				(washerDoor setCycle: Beg self)
			)
			(5
				(soundFx number: 528 loop: 1 play:)
				(= seconds 3)
			)
			(6
				(Print 57 45)
				(sarien
					show:
					startUpd:
					x: 278
					y: 181
					view: 417
					loop: 1
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 196 141 self
				)
			)
			(7
				(sarien
					view: 418
					loop: 0
					cycleSpeed: 6
					cel: 0
					setCycle: End self
				)
			)
			(8
				(soundFx number: 529 loop: 1 play:)
				(sarien loop: 1 cel: 0 setCycle: CT 2 1)
				(washerDoor cel: 0 setCel: 1)
				(= ticks 18)
			)
			(9
				(sarien setCycle: CT 5 1 self)
			)
			(10
				(washerDoor setCycle: Beg)
				(= seconds 2)
			)
			(11
				(sarien setCycle: End self)
				(soundFx number: 528 loop: 1 play:)
			)
			(12
				(= register 1)
				(ego
					loop: 2
					view: 39
					cycleSpeed: 30
					x: 210
					y: 97
					setCycle: Fwd
				)
				(sarien
					setLoop: 2
					x: 210
					y: 144
					cycleSpeed: 3
					cel: 5
					setCycle: Fwd
					setMotion: MoveTo 283 184 self
				)
			)
			(13
				(sarien dispose:)
				(= seconds 3)
			)
			(14
				(ego cycleSpeed: 7 setCycle: Fwd)
				(= seconds 3)
			)
			(15
				(helmet
					setStep: 10 6
					moveSpeed: 1
					setPri: 5
					setLoop: 3
					cycleSpeed: 0
					setCycle: Fwd
					setMotion: DPath 185 94 201 76 223 103 202 106 189 71 181 92 210 81 223 99
				)
				(= register 2)
				(ego cycleSpeed: 1 setCycle: Fwd)
				(= seconds 6)
			)
			(16
				(helmet dispose:)
				(= register 1)
				(ego cycleSpeed: 7 setCycle: Fwd)
				(= seconds 4)
			)
			(17
				(ego cycleSpeed: 30 setCycle: Fwd)
				(= seconds 3)
			)
			(18
				(= register 0)
				(ego setCycle: 0)
				(= seconds 2)
			)
			(19
				(ego
					x: 191
					y: 118
					cycleSpeed: 6
					loop: 3
					cel: 0
					setPri: 6
					setCycle: 0
				)
				(soundFx number: 528 loop: 1 play:)
				(washerDoor setCycle: End self)
			)
			(20
				(ego setCel: 1)
				(= cycles (ego cycleSpeed?))
			)
			(21
				(ego setPri: -1 setCycle: End self)
			)
			(22
				(ego loop: 4 cel: 0 setCycle: End self)
				(SolvePuzzle 5 164)
			)
			(23
				(Print 57 46)
				(Bset 65)
				(if (ego has: 0) (Print 57 47) else (Print 57 48))
				(= temp0 1)
				(while (< temp0 19)
					(ego put: temp0)
					(++ temp0)
				)
				(ego get: 19)
				(ego put: 19 57)
				(if (not (ego has: 0)) (ego get: 10) (= buckazoids 1))
				(scraps init:)
				(DeltaurRegion egoStatus: 1)
				(EgoStatusCheck)
				(ego loop: 4)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance openWasherDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset 69)
				(ego setMotion: PolyPath 192 140 self)
			)
			(1
				(ego
					view: 39
					loop: 0
					cel: 0
					x: 192
					y: 141
					setCycle: CT 5 1 self
				)
			)
			(2
				(soundFx number: 529 loop: 1 play:)
				(ego setCycle: End)
				(washerDoor startUpd: cel: 0 setCycle: End self)
			)
			(3
				(EgoStatusCheck)
				(ego loop: 6 setHeading: 45)
				((curRoom obstacles?) delete: closedPoly)
				((curRoom obstacles?) add: openPoly)
				(washerDoor stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance walkInRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					loop: 7
					ignoreActors: 1
					setMotion: MoveTo 230 158 self
				)
			)
			(1
				(ego ignoreActors: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance walkOutRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1 setMotion: MoveTo 270 177 self)
			)
			(1 (curRoom newRoom: 66))
		)
	)
)

(instance openVentScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(vent setCycle: End self)
			)
			(1
				(vent stopUpd:)
				(HandsOn)
				(client cue:)
				(self dispose:)
			)
		)
	)
)

(instance climbOutOfVent of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 37 loop: 1 x: 111 y: 118)
				(if (not (Btst 37))
					(self setScript: openVentScript)
				else
					(self cue:)
				)
				(Bset 37)
			)
			(1 (ego setCycle: End self))
			(2
				(HandsOn)
				(EgoStatusCheck)
				(ego loop: 2)
				(self dispose:)
			)
		)
	)
)

(instance killEgoTimeOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 60))
			(1
				(Print 57 49)
				(= seconds 20)
			)
			(2
				(Print 57 50)
				(= seconds 10)
			)
			(3
				(= local212 1)
				(self dispose:)
			)
		)
	)
)

(instance egoTestVent of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(trunk ignoreActors: 1)
				(if
					(and
						(== (ego x?) (trunk x?))
						(== (ego y?) (trunk y?))
					)
					(self changeState: 2)
				else
					(ego setMotion: PolyPath 110 117 self)
				)
			)
			(1
				(ego setMotion: MoveTo 104 115 self)
			)
			(2
				(ego
					setPri: (+ (trunk priority?) 1)
					view: 36
					cycleSpeed: 12
					loop: 3
					y: (trunk y?)
					cel: 0
					setMotion: 0
					setCycle: End self
				)
			)
			(3
				(ego loop: 4 x: 97 y: 96 cel: 5)
				(= register 0)
				(= ticks 18)
			)
			(4
				(ego setCycle: RangeOsc 5 5 6 self)
			)
			(5
				(Print 57 51)
				(ego
					posn: (trunk x?) (trunk y?)
					loop: 3
					setCel: 4
					setPri: -1
				)
				(self cue:)
			)
			(6 (ego setCycle: Beg self))
			(7
				(EgoStatusCheck)
				(ego loop: 7)
				(trunk show:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance cantReachIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 104 115 self)
			)
			(1
				(= register 3)
				(ego
					view: 36
					cel: 0
					loop: 4
					cycleSpeed: 12
					setCycle: CT 2 1 self
				)
			)
			(2
				(ego setCycle: RangeOsc 3 1 2 self)
			)
			(3
				(Print 57 52)
				(ego setCycle: Beg self)
			)
			(4
				(EgoStatusCheck)
				(ego loop: 7)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
