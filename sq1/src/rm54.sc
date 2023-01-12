;;; Sierra Script 1.0 - (do not remove this comment)
(script# 54)
(include game.sh)
(use Main)
(use Intrface)
(use Deltaur)
(use SQRoom)
(use RangeOsc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Grooper)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm54 0
)

(local
	local0
	[trunkPts 8] = [121 132 161 120 189 133 150 146]
	[trunkForwardPts 54] = [265 189 0 189 0 0 319 0 319 189 129 96 126 95 116 93 105 96 115 101 104 105 90 101 79 104 91 110 77 115 60 109 52 112 63 119 36 125 78 149 50 159 76 174 108 161 137 177 175 179 222 165 260 188]
	[trunkBackPts 36] = [84 147 50 159 73 175 108 160 131 176 174 178 221 162 268 189 0 189 0 1 319 1 319 189 129 95 110 100 132 110 91 121 74 113 38 121]
)
(instance rm54 of SQRoom
	(properties
		picture 54
	)
	
	(method (init)
		(self setRegions: DELTAUR)
		(HandsOff)
		(= currentFloor 2)
		(LoadMany VIEW 154 37 39 51 36 1 61 2)
		(LoadMany SOUND 514 526 512 513 515 538)
		(ego init:)
		(vent init:)
		(roomWithTrunkForwardPoly points: @trunkForwardPts size: 27)
		(trunkPoly points: @trunkPts size: 4)
		(roomWithTrunkBackPoly points: @trunkBackPts size: 18)
		(if (or (Btst fBeenInLaundry) (Btst fTrunkMovedToLaundry))
			(self addObstacle: roomWithTrunkForwardPoly)
		else
			(trunk init: ignoreActors: TRUE)
			(if (not (Btst fPushedTrunk))
				(self addObstacle: trunkPoly roomWithTrunkForwardPoly)
			else
				(self addObstacle: roomWithTrunkBackPoly)
			)
		)
		(if (== (DeltaurRegion egoStatus?) egoSpacesuit)
			(guard init: setScript: killEgoTimeOut)
			(LoadMany VIEW 479 66 417 48)
		)
		(switch prevRoomNum
			(61 (ego posn: 267 177))
			(55
				(ego view: 37 loop: 1 posn: 111 118)
			)
			(else  (ego posn: 69 170))
		)
		(super init:)
		(if (!= (theMusic number?) 538)
			(theMusic number: 538 loop: -1 play:)
		)
		(features
			add: ventShaft shadows floor1 airLock floor2 floor3 vaults
			eachElementDo: #init
		)
		(switch prevRoomNum
			(61
				(ego posn: 267 177)
				(self setScript: fromHall)
			)
			(55
				(self setScript: climbOutOfVent)
			)
			(else 
				(ego posn: 69 170)
				(self setScript: fromAirlock)
			)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			((or (self script?) (ego script?)) 0)
			(local0 (self setScript: killEgo))
			((& (ego onControl: 0) cBLUE) (self setScript: toHall))
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb verbLook)
			(if
			(or (Btst fTrunkMovedToLaundry) (== (DeltaurRegion egoStatus?) egoWithHelmet))
				(Print 54 0)
			else
				(Print 54 1)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (newRoom n)
		(if (!= n 57) (theMusic fade:))
		(super newRoom: n)
	)
)

(instance trunkPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance roomWithTrunkForwardPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance roomWithTrunkBackPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance vent of Prop
	(properties
		x 92
		y 49
		view 154
		cycleSpeed 12
	)
	
	(method (init)
		(super init: &rest)
		(if (Btst fOpenedVent1) (self setCel: (self lastCel:)))
		(self stopUpd:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if cel (Print 54 2) else (Print 54 3))
			)
			(verbUse
				(if (== theItem iKnife)
					(cond 
						(
							(and
								(or (Btst fTrunkMovedToLaundry) (Btst fBeenInLaundry))
								(== (DeltaurRegion egoStatus?) egoSpacesuit)
							)
							(curRoom setScript: cantReachIt)
						)
						((or (Btst fTrunkMovedToLaundry) (Btst fBeenInLaundry)) (Print 54 4))
						((not (not (vent cel?))) (Print 54 5))
						((Btst fHidingInTrunk) (Print 54 6))
						((not (Btst fPushedTrunk)) (curRoom setScript: cantReachIt))
						(else (curRoom setScript: climbInVent))
					)
				else
					(super doVerb: theVerb theItem)
				)
			)
			(verbDo
				(cond 
					(
						(and
							(or (Btst fTrunkMovedToLaundry) (Btst fBeenInLaundry))
							(== (DeltaurRegion egoStatus?) 0)
						)
						(curRoom setScript: cantReachIt)
					)
					((or (Btst fTrunkMovedToLaundry) (Btst fBeenInLaundry)) (Print 54 4))
					((Btst fHidingInTrunk) (Print 54 6))
					((not (Btst fPushedTrunk)) (curRoom setScript: cantReachIt))
					((not (vent cel?)) (curRoom setScript: egoTestVent))
					(else (curRoom setScript: climbInVent))
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance trunk of Prop
	(properties
		view 154
		loop 1
		cycleSpeed 6
	)
	
	(method (init)
		(super init: &rest)
		(if (Btst fTrunkMovedToLaundry) (Bclr fHidingInTrunk))
		(if (Btst fHidingInTrunk) (self cel: (self lastCel:)))
		(if (Btst fPushedTrunk)
			(= x 104)
			(= y 115)
		else
			(= x 162)
			(= y 138)
		)
	)
	
	(method (doVerb theVerb theItem)
		(if (not (Btst fTrunkMovedToLaundry))
			(switch theVerb
				(verbUse
					(if (== theItem iKnife)
						(if (Btst fHidingInTrunk)
							(curRoom setScript: closeTrunkScript)
						else
							(curRoom setScript: openTrunkScript)
						)
					else
						(super doVerb: theVerb theItem &rest)
					)
				)
				(verbDo
					(cond 
						((Btst fHidingInTrunk) (curRoom setScript: egoClimbInTrunk))
						((not (Btst fPushedTrunk)) (curRoom setScript: pushTheTrunkScript))
						(else (Print 54 7))
					)
				)
				(verbLook
					(if cel (Print 54 8) else (Print 54 9))
				)
				(else 
					(super doVerb: theVerb theItem &rest)
				)
			)
		else
			(Print 54 10)
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance guard of Actor
	(properties
		x 327
		y 223
		view 417
		cycleSpeed 3
		xStep 4
		moveSpeed 3
	)
	
	(method (init)
		(super init: &rest)
		(self hide: setCycle: Walk setLoop: GradualLooper)
	)
)

(instance fromAirlock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(EgoStatusCheck)
				(= ticks 18)
			)
			(1
				(ego setMotion: MoveTo 98 153 self)
			)
			(2
				(SolvePuzzle 1 fExitDeltaurAirlock)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance fromHall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(EgoStatusCheck)
				(= ticks 18)
			)
			(1
				(ego
					ignoreActors: 1
					loop: 7
					setMotion: MoveTo 227 160 self
				)
			)
			(2
				(ego ignoreActors: 0)
				(SolvePuzzle 1 fExitDeltaurAirlock)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance toHall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(guard setScript: 0)
				(= local0 0)
				(ego ignoreActors: TRUE setMotion: MoveTo 267 177 self)
			)
			(1 (curRoom newRoom: 61))
		)
	)
)

(instance egoClimbInTrunk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset 35)
				(Bclr 34)
				(guard setScript: 0)
				(= local0 0)
				(if
					(and
						(== (ego x?) (trunk x?))
						(== (ego y?) (trunk y?))
					)
					(self changeState: 2)
				else
					(ego
						setMotion: PolyPath (+ (trunk x?) 6) (+ (trunk y?) 4) self
					)
				)
			)
			(1
				(ego
					setMotion: MoveTo (trunk x?) (+ (trunk y?) 1) self
				)
			)
			(2
				(theMusic fade: 0 5 30 1)
				(SolvePuzzle 3 fExitVentilationShaft)
				(trunk startUpd: hide:)
				(ego
					view: 36
					loop: 1
					cycleSpeed: 12
					cel: 0
					y: (trunk y?)
					setCycle: EndLoop self
				)
			)
			(3
				(soundFx number: 514 loop: 1 play:)
				(vent hide:)
				(curRoom drawPic: 99 IRISOUT)
				(theMusic2 number: 507 loop: 1 play: 0 fade: 127 5 30 0)
				(ego
					view: 51
					loop: 0
					x: (- (trunk x?) 1)
					cel: 0
					setPri: 15
					setStep: (* (ego xStep?) 3) (* (ego yStep?) 3)
					setCycle: Forward
					setMotion: MoveTo 104 115 self
				)
			)
			(4
				(ego setMotion: MoveTo 232 184 self)
			)
			(5
				(if (== (ego x?) 232)
					(ego setMotion: MoveTo 300 (- (ego y?) 40) self)
				else
					(ego setMotion: MoveTo 232 (- (ego y?) 40) self)
				)
			)
			(6
				(if (> (ego y?) 29)
					(= state (- state 2))
					(self cue:)
				else
					(ego setMotion: MoveTo 50 23 self)
				)
			)
			(7
				(ego setMotion: JumpTo 153 188 self)
			)
			(8
				(soundFx number: 526 loop: 1 play:)
				(ego setMotion: JumpTo 308 103 self)
			)
			(9
				(soundFx play:)
				(ego setMotion: JumpTo 86 183 self)
			)
			(10
				(soundFx play:)
				(ego setMotion: JumpTo 22 137 self)
			)
			(11
				(soundFx play:)
				(ego setMotion: JumpTo 111 185 self)
			)
			(12
				(soundFx play:)
				(ego setMotion: JumpTo 188 185 self)
			)
			(13
				(soundFx play:)
				(ego setMotion: MoveTo 178 176 self)
			)
			(14
				(ego setMotion: MoveTo 289 141 self)
			)
			(15
				(ego setMotion: MoveTo 124 148 self)
			)
			(16
				(ego setMotion: MoveTo 222 110 self)
			)
			(17
				(ego setMotion: MoveTo 104 115 self)
			)
			(18
				(EgoStatusCheck)
				(ego
					loop: 7
					setStep: (/ (ego xStep?) 2) (/ (ego yStep?) 2)
				)
				(HandsOn)
				(theMusic play: 0 fade: 127 25 10 0)
				(theMusic2 fade:)
				(curRoom newRoom: 57)
			)
		)
	)
)

(instance climbInVent of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fOpenedVent1)
				(guard setScript: 0)
				(= local0 0)
				(HandsOff)
				(if
					(and
						(== (ego x?) (trunk x?))
						(== (ego y?) (trunk y?))
					)
					(self changeState: 2)
				else
					(ego
						setMotion: PolyPath (+ (trunk x?) 6) (+ (trunk y?) 4) self
					)
				)
			)
			(1
				(ego
					setMotion: MoveTo (trunk x?) (+ (trunk y?) 1) self
				)
			)
			(2
				(ego
					setPri: (+ (trunk priority?) 1)
					view: 36
					y: (trunk y?)
					loop: 3
					cycleSpeed: 12
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(if (not (vent cel?))
					(self setScript: fiddleWithVent self)
				else
					(= ticks 18)
				)
			)
			(4
				(trunk startUpd: setPri: 2)
				(ego
					view: 37
					x: 97
					y: 96
					loop: 0
					cel: 0
					setPri: -1
					setCycle: EndLoop self
				)
			)
			(5 (ego hide:) (= ticks 18))
			(6
				(curRoom newRoom: 55)
				(self dispose:)
			)
		)
	)
)

(instance fiddleWithVent of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					cycleSpeed: 12
					loop: 4
					x: 97
					y: 96
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(1
				(soundFx number: 512 loop: 1 play:)
				(vent setCel: 1)
				(ego setCycle: CycleTo 6 1)
				(= cycles (ego cycleSpeed?))
			)
			(2
				(vent setCel: 2)
				(ego setCel: 7)
				(= cycles (ego cycleSpeed?))
			)
			(3
				(vent setCel: 3)
				(ego setCel: 8)
				(= cycles (ego cycleSpeed?))
			)
			(4
				(vent setCel: 4)
				(= cycles (ego cycleSpeed?))
			)
			(5 (self dispose:))
		)
	)
)

(instance egoTestVent of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
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
				(ego setMotion: MoveTo 104 116 self)
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
					setCycle: EndLoop self
				)
			)
			(3
				(trunk show:)
				(ego loop: 4 x: 97 y: 96 cel: 5)
				(= register 0)
				(self cue:)
			)
			(4
				(ego setCycle: RangeOsc 5 5 6 self)
			)
			(5
				(Print 54 11)
				(ego
					posn: (trunk x?) (trunk y?)
					loop: 3
					setCel: 4
					setPri: -1
				)
				(self cue:)
			)
			(6 (ego setCycle: BegLoop self))
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

(instance openVentScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(vent cycleSpeed: 12 setCycle: EndLoop self)
			)
			(1
				(HandsOn)
				(client cue:)
				(self dispose:)
			)
		)
	)
)

(instance closeVentScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(vent setCycle: BegLoop self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance openTrunkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fHidingInTrunk)
				(if
					(and
						(== (ego x?) (trunk x?))
						(== (ego y?) (trunk y?))
					)
					(self changeState: 2)
				else
					(ego
						setMotion: PolyPath (+ (trunk x?) 6) (+ (trunk y?) 4) self
					)
				)
			)
			(1
				(ego
					setMotion: MoveTo (trunk x?) (+ (trunk y?) 1) self
				)
			)
			(2
				(trunk startUpd: hide:)
				(soundFx number: 513 loop: 1 play:)
				(ego
					view: 36
					cycleSpeed: 12
					loop: 5
					y: (trunk y?)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(ego
					x: (- (trunk x?) 1)
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(trunk show: cel: 1 stopUpd:)
				(EgoStatusCheck)
				(ego x: (trunk x?) loop: 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance closeTrunkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bclr 34)
				(if
					(and
						(== (ego x?) (trunk x?))
						(== (ego y?) (trunk y?))
					)
					(self changeState: 2)
				else
					(ego
						setMotion: PolyPath (+ (trunk x?) 6) (+ (trunk y?) 4) self
					)
				)
			)
			(1
				(ego
					setMotion: MoveTo (trunk x?) (+ (trunk y?) 1) self
				)
			)
			(2
				(soundFx number: 514 loop: 1 play:)
				(trunk startUpd: hide:)
				(ego
					view: 36
					setLoop: 0
					x: (- (trunk x?) 1)
					y: (trunk y?)
					cycleSpeed: 12
					cel: 4
					setCycle: BegLoop self
				)
			)
			(3
				(ego setLoop: 5 x: (trunk x?) cel: 2 setCycle: BegLoop self)
			)
			(4
				(trunk show: cel: 0 stopUpd:)
				(EgoStatusCheck)
				(ego loop: 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance pushTheTrunkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset 35)
				(if
					(and
						(== (ego x?) (trunk x?))
						(== (ego y?) (trunk y?))
					)
					(self changeState: 1)
				else
					(ego
						setMotion: PolyPath (+ (trunk x?) 3) (+ (trunk y?) 1) self
					)
				)
			)
			(1
				((curRoom obstacles?)
					delete: roomWithTrunkForwardPoly trunkPoly
				)
				(trunk startUpd: hide:)
				(soundFx number: 515 loop: 1 play: hold: 1)
				(ego
					view: 36
					setLoop: 2
					x: (+ (trunk x?) 1)
					y: (trunk y?)
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 104 115 self
				)
			)
			(2
				(soundFx stop: hold: 0)
				((curRoom obstacles?) add: roomWithTrunkBackPoly)
				(trunk x: 104 y: 115 show: stopUpd:)
				(EgoStatusCheck)
				(ego loop: 7)
				(= ticks 18)
			)
			(3 (HandsOn) (self dispose:))
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
					cycleSpeed: 9
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(ego setCycle: RangeOsc 3 1 2 self)
			)
			(3
				(Print 54 12)
				(ego setCycle: BegLoop self)
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

(instance climbOutOfVent of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset 36)
				(ego view: 37 loop: 1 cycleSpeed: 12 x: 111 y: 118)
				(if (not (Btst fOpenedVent1))
					(self setScript: openVentScript)
				else
					(self cue:)
				)
			)
			(1 (ego setCycle: EndLoop self))
			(2
				(SolvePuzzle 1 fExitDeltaurAirlock)
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
				(Print 54 13)
				(= seconds 20)
			)
			(2
				(Print 54 14)
				(= seconds 10)
			)
			(3
				(= local0 1)
				(self dispose:)
			)
		)
	)
)

(instance killEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(guard show: setMotion: MoveTo 270 175 self)
			)
			(1 (Face ego guard self))
			(2
				(cond 
					((& (ego onControl: 0) $0008) (guard setLoop: 1))
					((& (ego onControl: 0) $0010) (guard setLoop: 3))
					(else (guard setLoop: 5))
				)
				(guard view: 415 cel: 0 setCycle: EndLoop self)
			)
			(3
				(ego
					view: 48
					setLoop: 1
					cycleSpeed: 6
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(EgoDead 945 0 0 54 15)
				(self dispose:)
			)
		)
	)
)

(instance ventShaft of RegionFeature
	(properties
		x 72
		y 42
		description {vent shaft}
		onMeCheck $2000
		lookStr {A large vent shaft protrudes from the left wall of the storage room.}
		level 2
	)
)

(instance shadows of RegionFeature
	(properties
		description {shadows}
		onMeCheck cLRED
		lookStr {Various pipes, ducts and other innocuous pieces of equipment form pronounced shadows throughout the room.}
		level 2
	)
)

(instance airLock of RegionFeature
	(properties
		description {airlock}
		onMeCheck $0800
		lookStr {That is the way back to the airlock, which is completely sealed and inaccessable to you.}
		level 2
	)
)

(instance vaults of RegionFeature
	(properties
		x 190
		y 81
		description {storage vault}
		onMeCheck $4000
		lookStr {Two enormous storage vaults take up one whole wall of the room.}
		level 2
	)
)

(instance floor1 of RegionFeature
	(properties
		description {storage room}
		sightAngle 0
		onMeCheck $0008
		level 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb verbLook)
			(curRoom doVerb: theVerb &rest)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance floor2 of RegionFeature
	(properties
		description {storage room}
		sightAngle 0
		onMeCheck $0010
		level 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb verbLook)
			(curRoom doVerb: theVerb &rest)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance floor3 of RegionFeature
	(properties
		description {storage room}
		sightAngle 0
		onMeCheck $0020
		level 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb verbLook)
			(curRoom doVerb: theVerb &rest)
		else
			(super doVerb: theVerb &rest)
		)
	)
)
