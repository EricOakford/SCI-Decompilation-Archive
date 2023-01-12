;;; Sierra Script 1.0 - (do not remove this comment)
(script# DELTAUR) ;703
(include game.sh)
(use Main)
(use Intrface)
(use RegionPath)
(use SQEgo)
(use RangeOsc)
(use PolyPath)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Grooper)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	DeltaurRegion 0
	EgoStatusCheck 1
	proc703_2 2
	sarienOfficer1 14
	sarienOfficer2 15
	sarienOfficer3 16
	sarienOfficer4 17
	shootTheEgo1 18
	gd1 21
	firePulsar 22
	countDown 23
)

(local
	[local0 31] = [32767 61 352 68 233 173 61 174 190 175 241 168 352 68 32767 62 -6 237 60 185 96 173 201 173 96 173 60 185 -6 237 -32768]
	[local31 47] = [32767 62 -5 77 179 77 103 120 32767 63 187 58 161 76 205 76 198 88 38 81 0 81 32767 61 319 79 121 79 121 67 146 46 32767 66 62 175 128 136 63 107 71 106 200 132 333 118 -32768]
	[local78 17] = [32767 60 -14 186 103 186 -14 186 32767 59 333 186 137 186 333 186 -32768]
	[local95 25] = [32767 60 0 79 130 79 151 53 293 52 151 53 130 79 0 79 32767 59 320 79 137 83 320 79 -32768]
	[str 40]
	theSarienGuardX
	clientX
	local162
	[detonationWindowPosn 28] = [19 4 19 4 19 4 19 4 19 4 19 4 275 76 19 4 276 4 276 95 19 67 19 4 19 4 276 4]
	theTimeID
	local192
	theSarienGuard
	doCount
	lookCount
	tasteCount
	smellCount
	talkCount
)

(procedure (EgoStatusCheck &tmp temp0)
	(switch (DeltaurRegion egoStatus?)
		(egoSpacesuit (NormalEgo temp0 1 61))
		(egoWithHelmet (NormalEgo temp0 416 65))
		(egoNoHelmet (NormalEgo temp0 2 62))
	)
	(ego illegalBits: cWHITE)
)

(procedure (proc703_2 param1 theTheEgo param3 &tmp theEgo temp1)
	(if (< argc 2)
		(= theEgo ego)
	else
		(= theEgo theTheEgo)
	)
	(if (IsObject theEgo)
		(= temp1
			(GetAngle
				(param1 x?)
				(param1 y?)
				(theEgo x?)
				(theEgo y?)
			)
		)
	else
		(= temp1
			(GetAngle (param1 x?) (param1 y?) theEgo param3)
		)
	)
	(return
		(cond 
			((< temp1 44) (return 4))
			((< temp1 134) (return 0))
			((< temp1 179) (return 2))
			((< temp1 224) (return 3))
			((< temp1 314) (return 1))
			((< temp1 359) (return 5))
		)
	)
)

(procedure (DisplayDetonationBox param1)
	(Display 703 0 p_restore theTimeID)
	(Format @str 703 1 (/ param1 60) (mod param1 60))
	(= theTimeID
		(Display @str
			p_at (detonationWindow x?) (detonationWindow y?)
			p_color myTextColor12
			p_font 2
			p_save
		)
	)
)

(procedure (localproc_00b9 param1)
	(return
		(if (IsObject param1)
			(return (param1 busy?))
		else
			(return 0)
		)
	)
)

(class RegionFeature of Feature
	(properties
		level 0
	)
	
	(method (handleEvent event &tmp temp0)
		(cond 
			((event claimed?) (return TRUE))
			(
				(and
					(== (event type?) userEvent)
					(self onMe: event)
					(self isNotHidden:)
				)
				(self
					x: ((user curEvent?) x?)
					y: ((user curEvent?) y?)
				)
				(CueObj
					state: 0
					cycles: 0
					client: self
					theVerb: (event message?)
					theInvItem:
						(if
							(and
								theIconBar
								(== (event message?) verbUse)
								inventory
							)
							(inventory indexOf: (theIconBar curInvIcon?))
						else
							0
						)
				)
				(event claimed: TRUE)
				(if
					(and
						(user canControl:)
						(!= _approachVerbs ftrDefault)
						(&
							_approachVerbs
							(<< $0001 (- (event message?) verbLook))
						)
					)
					(ego
						setMotion: PolyPath approachX (+ (ego z?) approachY) CueObj
					)
				else
					(ego setMotion: 0)
					(CueObj changeState: 3)
				)
			)
		)
		(return (event claimed?))
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(cond 
					((and (< level 3) (!= currentFloor level))
						(if (== currentFloor 2)
							(Print 703 2)
						else
							(Print 703 3)
						)
					)
					((not lookStr)
						(switch (mod (++ lookCount) 6)
							(0 (Print 703 4))
							(1 (Print 703 5))
							(2 (Print 703 6))
							(3 (Print 703 7))
							(4 (Print 703 8))
							(5 (Print 703 9))
							(6 (Print 703 10))
						)
					)
					(else (super doVerb: theVerb theItem &rest))
				)
			)
			(verbTalk
				(if
					(or
						(and (!= level 3) (!= currentFloor level))
						(== level 4)
					)
					(if (== currentFloor 2)
						(Print 703 11)
					else
						(Print 703 12)
					)
				else
					(switch (mod (++ talkCount) 4)
						(0 (Print 703 13))
						(1 (Print 703 14))
						(2 (Print 703 15))
						(3 (Print 703 16))
						(4 (Print 703 17))
					)
				)
			)
			(verbDo
				(if
					(or
						(and (!= level 3) (!= currentFloor level))
						(== level 4)
					)
					(if (== currentFloor 2)
						(Print 703 18)
					else
						(Print 703 19)
					)
				else
					(switch (mod (++ doCount) 5)
						(0 (Print 703 20))
						(1 (Print 703 21))
						(2 (Print 703 22))
						(3 (Print 703 23))
						(4 (Print 703 24))
						(5 (Print 703 25))
					)
				)
			)
			(verbSmell
				(if
					(or
						(and (!= level 3) (!= currentFloor level))
						(== level 4)
					)
					(if (== currentFloor 2)
						(Print 703 26)
					else
						(Print 703 27)
					)
				else
					(switch (mod (++ smellCount) 5)
						(0 (Print 703 28))
						(1 (Print 703 29))
						(2 (Print 703 30))
						(3 (Print 703 31))
						(4 (Print 703 32))
						(5 (Print))
					)
				)
			)
			(verbTaste
				(if
					(or
						(and (!= level 3) (!= currentFloor level))
						(== level 4)
					)
					(if (== currentFloor 2)
						(Print 703 33)
					else
						(Print 703 34)
					)
				else
					(switch (mod (++ tasteCount) 5)
						(0 (Print 703 35))
						(1 (Print 703 36))
						(2 (Print 703 37))
						(3 (Print 703 38))
						(4 (Print 703 39))
					)
				)
			)
			(verbUse
				(switch theItem
					(iPulseray
						(if
							(and
								(OneOf curRoomNum 59 60 62 61 63)
								(!= currentFloor level)
							)
							(Print 703 40)
						else
							(= theSarienGuard 0)
							(ego setScript: firePulsar)
						)
					)
					(13
						(ego setScript: (ScriptID 707 0))
					)
					(else  (Print 703 41))
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(class DeltaurRegion of Region
	(properties
		lookStr {You're in another area of the Deltaur.}
		eDoor1 0
		eDoor2 0
		egoStatus 0
		numGrenades 2
		timesShownID 0
		theRoom 0
		timeToBlow 300
		timerWindow 0
		timeToBlowLast 0
	)
	
	(method (init)
		(super init: &rest)
		(= eDoor2 (= eDoor1 0))
		(if (OneOf curRoomNum 59 60 61 62 63 66)
			(LoadMany VIEW 69 417 66)
			(LoadMany TEXT 170)
		)
		(if (!= (DeltaurRegion egoStatus?) egoWithHelmet)
			(LoadMany VIEW 415 48 50 479)
		)
		(if (ego has: iPulseray) (LoadMany VIEW 41 419 479))
		(features add: pipes)
		(if (Btst fStartedSelfDestruct) (LoadMany VIEW 3))
		(if (ego has: iGrenade)
			(LoadMany SOUND 518 519)
			(LoadMany VIEW 75)
		)
		(if (or (!= egoStatus egoWithHelmet) (ego has: iPulseray))
			(Load SOUND 312)
		)
		(if (OneOf curRoomNum 59 60 66 62)
			(features add: elevators)
			(LoadMany SOUND 315)
		)
		(if (OneOf curRoomNum 62 66) (features add: tubes))
		(if (OneOf curRoomNum 59 60 62 61 63)
			(features add: upperLevel lowerLevel)
		)
		(features eachElementDo: #init doit:)
		(if (OneOf curRoomNum 61 62)
			(sarienOfficer1
				level: 2
				shootEgo: shootTheEgo1
				blastID: blast1
				regionPathID: guardPath1
				setMotion: guardPath1
				_head: sarienOfficer1Head
				init:
				activate:
			)
			(sarienOfficer1Head init: sarienOfficer1 setLoop: 8)
		)
		(if (OneOf curRoomNum 61 62 66 63)
			(sarienOfficer2
				level: 1
				shootEgo: shootTheEgo1
				blastID: blast1
				regionPathID: guardPath2
				setMotion: guardPath2
				_head: sarienOfficer2Head
				init:
				activate:
			)
			(sarienOfficer2Head init: sarienOfficer2 setLoop: 8)
		)
		(if (OneOf curRoomNum 60 59)
			(sarienOfficer3
				level: 2
				regionPathID: guardPath3
				shootEgo: shootTheEgo1
				blastID: blast1
				setMotion: guardPath3
				_head: sarienOfficer3Head
				init:
				activate:
			)
			(sarienOfficer3Head init: sarienOfficer3 setLoop: 8)
			(sarienOfficer4
				level: 1
				regionPathID: guardPath4
				shootEgo: shootTheEgo1
				blastID: blast1
				setMotion: guardPath4
				_head: sarienOfficer4Head
				init:
				activate:
			)
			(sarienOfficer4Head init: sarienOfficer4 setLoop: 8)
		)
		(if
			(and
				(!= (theMusic number?) 508)
				(not (OneOf curRoomNum 53 54 55 57 58 64))
			)
			(theMusic number: 508 loop: -1 hold: 0 flags: mNOPAUSE play:)
		)
		(if (Btst 53)
			(detonationWindow
				x: [detonationWindowPosn (* (- curRoomNum 54) 2)]
				y: [detonationWindowPosn (+ (* (- curRoomNum 54) 2) 1)]
			)
			(detonationWindow init: stopUpd:)
		)
		(if (< prevRoomNum 50)
			(Print 703 42)
			(if (== curRoomNum 54)
				(= egoStatus egoSpacesuit)
				(ego get: iKnife)
			else
				(= egoStatus egoWithHelmet)
			)
			(EgoStatusCheck)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and (Btst fStartedSelfDestruct) (!= theRoom curRoomNum))
			(= theRoom curRoomNum)
			(self setScript: countDown)
			(= timeToBlowLast 0)
			(detonationWindow
				x: [detonationWindowPosn (* (- curRoomNum 54) 2)]
				y: [detonationWindowPosn (+ (* (- curRoomNum 54) 2) 1)]
			)
			(= theTimeID
				(Display 703 43
					p_at (detonationWindow x?) (detonationWindow y?)
					p_color myTextColor12
					p_font 2
					p_save
				)
			)
		)
		(if (and (Btst fStartedSelfDestruct) (!= timeToBlowLast timeToBlow))
			(DisplayDetonationBox (= timeToBlowLast timeToBlow))
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbUse
				(switch theItem
					(iGrenade
						(if (!= curRoomNum 64)
							(curRoom setScript: (ScriptID 707 0))
						)
					)
					(iPulseray
						(if
							(and
								(not (curRoom script?))
								(not (OneOf curRoomNum 59 60 62 61 63))
							)
							(= theSarienGuard 0)
							(curRoom setScript: firePulsar)
						else
							(super doVerb: theVerb theItem &rest)
						)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (newRoom n)
		(ego ignoreActors: 1)
		(= keep (OneOf n 54 55 57 58 59 60 61 62 63 64 65 66 67))
		(= initialized FALSE)
		(if (OneOf n 53 54 55 57 58 64) (theMusic fade:))
		(super newRoom: n &rest)
	)
)

(class sarienGuard of Actor
	(properties
		description {sarien officer}
		lookStr {It's one of the Sarien gaurds.}
		view 417
		loop 0
		cel 0
		xStep 4
		guardLocked 0
		regionPathID 0
		level 0
		shotsFired 0
		dead 0
		guardSalutes 0
		shootEgo 0
		gd 0
		lastLoop 0
		blastID 0
		_head 0
		normal TRUE
		moveHead TRUE
	)
	
	(method (init)
		(self setCycle: StopWalk 66 setLoop: GradualLooper)
		(super init: &rest)
	)
	
	(method (doit)
		(cond 
			(
				(and
					(self inSameRoom:)
					(> 280 x)
					(> x 40)
					(> 190 y)
					(> y 10)
				)
				(if (& signal ignrAct) (self ignoreActors: 0))
				(cond 
					(
						(and
							(not script)
							regionPathID
							(== view 66)
							(> (self distanceTo: ego) 35)
						)
						(self guardSalutes: 0 activate:)
					)
					((and (OneOf curRoomNum 63 61) (< y 71)) 0)
					(
						(and
							(not guardLocked)
							(not script)
							(== level currentFloor)
							(not (ego script?))
							(not (localproc_00b9 (DeltaurRegion eDoor1?)))
							(not (localproc_00b9 (DeltaurRegion eDoor2?)))
							(not dead)
							(!= (DeltaurRegion egoStatus?) 1)
							(not (curRoom script?))
						)
						(self setScript: shootEgo)
					)
					(
						(and
							(not guardLocked)
							(not (ego script?))
							(not script)
							(not (localproc_00b9 (DeltaurRegion eDoor1?)))
							(not (localproc_00b9 (DeltaurRegion eDoor2?)))
							(not dead)
							(not (curRoom script?))
							regionPathID
							(== (DeltaurRegion egoStatus?) egoWithHelmet)
							(not guardSalutes)
							(== level currentFloor)
							(< (self distanceTo: ego) 35)
						)
						(self guardSalutes: TRUE setScript: saluteScript)
					)
					(
						(and
							(not guardLocked)
							regionPathID
							guardSalutes
							(> (self distanceTo: ego) 40)
						)
						(self guardSalutes: 0 activate:)
					)
					(
					(and regionPathID (!= currentFloor level) (== view 415)) (self guardSalutes: FALSE activate:))
				)
			)
			((not (& signal ignrAct)) (self ignoreActors: TRUE))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(if _head (_head dispose:))
		(super dispose:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbUse
				(if (== level currentFloor)
					(switch theItem
						(iPulseray
							(= theSarienGuard self)
							(ego setScript: firePulsar)
						)
						(iCartridge (Print 703 44))
						(iSarienIDCard (Print 703 45))
						(else 
							(super doVerb: theVerb theItem &rest)
						)
					)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(verbTalk
				(cond 
					((and (== curRoomNum 66) (not regionPathID))
						(if (== (DeltaurRegion egoStatus?) egoWithHelmet)
							(self setScript: (ScriptID 705 0))
						else
							(Print 703 46)
						)
					)
					(
						(and
							(< (ego distanceTo: self) 35)
							(== (self level?) currentFloor)
						)
						((ScriptID 705 1)
							register: (= local192 (mod (++ local192) 5))
						)
						(self setScript: (ScriptID 705 1))
					)
					(else (Print 703 47))
				)
			)
			(2
				(if (== currentFloor level)
					(Print 703 48)
				else
					(Print 703 49)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (stopUpd)
		(if _head (_head stopUpd:))
		(super stopUpd:)
	)
	
	(method (hide)
		(if _head (_head hide:))
		(super hide:)
	)
	
	(method (headView param1)
		(_head view: param1)
		(if (not (& (_head signal?) hideActor)) (_head showSelf:))
	)
	
	(method (activate param1 &tmp temp0)
		(if argc (= temp0 param1) else (= temp0 1))
		(if (IsObject blastID) (blastID dispose:))
		(if (== script shootEgo) (shootEgo dispose:))
		(if temp0
			(if (IsObject _head) (= moveHead 1) (_head init: self))
			(self
				loop: lastLoop
				dead: 0
				ignoreActors: 0
				show:
				view: 417
				setStep: 4 2
				moveSpeed: 6
				cycleSpeed: 6
				setLoop: -1
				setCycle: StopWalk 66
				setLoop: GradualLooper
				setMotion: regionPathID
				shotsFired: 0
				setScript: 0
			)
		else
			(self hide: setMotion: 0)
		)
	)
	
	(method (pushToNextRoom)
		(if regionPathID
			(while
				(and
					(!=
						(regionPathID at: (+ (regionPathID value?) 1))
						32767
					)
					(not (regionPathID atEnd:))
				)
				(regionPathID next:)
			)
			(_head dispose:)
			(regionPathID moveDone:)
			(self activate:)
		)
	)
	
	(method (inSameRoom)
		(return
			(if regionPathID
				(return (== curRoomNum (regionPathID currentRoom?)))
			else
				(return TRUE)
			)
		)
	)
	
	(method (setToBeginning)
		(if regionPathID
			(regionPathID value: -1)
			(self activate: TRUE)
		)
	)
)

(instance detonationWindow of View
	(properties
		lookStr {It's the amount of time remaining before you're pushin' up daisies, Roger!!!!}
		view 3
		priority 15
		signal fixPriOn
	)
)

(instance sarienOfficer1 of sarienGuard
	(properties)
)

(instance sarienOfficer2 of sarienGuard
	(properties)
)

(instance sarienOfficer3 of sarienGuard
	(properties)
)

(instance sarienOfficer4 of sarienGuard
	(properties)
)

(instance sarienOfficer1Head of Head
	(properties
		description {sarien officer}
		lookStr {It's a Sarien officer.}
		view 66
		cycleSpeed 210
	)
	
	(method (doVerb theVerb theItem)
		(sarienOfficer1 doVerb: theVerb theItem)
	)
)

(instance sarienOfficer2Head of Head
	(properties
		description {sarien officer}
		lookStr {It's a Sarien officer.}
		view 66
		cycleSpeed 210
	)
	
	(method (doVerb theVerb theItem)
		(sarienOfficer2 doVerb: theVerb theItem)
	)
)

(instance sarienOfficer3Head of Head
	(properties
		description {sarien officer}
		lookStr {It's a Sarien officer.}
		view 66
		cycleSpeed 210
	)
	
	(method (doVerb theVerb theItem)
		(sarienOfficer3 doVerb: theVerb theItem)
	)
)

(instance sarienOfficer4Head of Head
	(properties
		description {sarien officer}
		lookStr {It's a Sarien officer.}
		view 66
		cycleSpeed 210
	)
	
	(method (doVerb theVerb theItem)
		(sarienOfficer4 doVerb: theVerb theItem)
	)
)

(instance blast of Prop
	(properties
		view 479
		loop 15
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 6
	)
)

(instance blast1 of Prop
	(properties
		view 479
		loop 15
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 6
	)
)

(instance guardPath1 of RegionPath
	(properties
		theRegion 703
	)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (sarienOfficer1 inSameRoom:) (== curRoomNum 61))
			(cond 
				(
					(and
						(<= (sarienOfficer1 y?) 145)
						(!= (sarienOfficer1 priority?) 2)
					)
					(sarienOfficer1 guardLocked: 1 setPri: 2)
				)
				(
					(and
						(> (sarienOfficer1 y?) 145)
						(== (sarienOfficer1 priority?) 2)
					)
					(sarienOfficer1 guardLocked: 0 setPri: -1)
				)
			)
		)
	)
	
	(method (at param1)
		(return [local0 param1])
	)
)

(instance guardPath2 of RegionPath
	(properties
		theRegion 703
	)
	
	(method (at param1)
		(return [local31 param1])
	)
)

(instance guardPath3 of RegionPath
	(properties
		theRegion 703
	)
	
	(method (at param1)
		(return [local78 param1])
	)
)

(instance guardPath4 of RegionPath
	(properties
		theRegion 703
	)
	
	(method (at param1)
		(return [local95 param1])
	)
)

(instance shootTheEgo1 of Script
	(properties)
	
	(method (changeState newState &tmp egoX egoY)
		(switch (= state newState)
			(0
				(if (not (client regionPathID?))
					(client cel: 0 setMotion: 0 view: 415)
				)
				(client setLoop: (proc703_2 client ego))
				(= ticks 18)
			)
			(1
				(if (< (+ (client shotsFired?) 1) 5)
					(client shotsFired: (+ (client shotsFired?) 1))
					(= register 0)
				else
					(= register 1)
					(HandsOff)
				)
				(if (== (client view?) 415)
					(sarienShot play:)
					(client cel: (- (client lastCel:) 2) setCycle: EndLoop self)
				else
					(client view: 415 setMotion: 0 cel: 0 setCycle: EndLoop self)
				)
			)
			(2
				(if register
					(= egoX (ego x?))
					(= egoY (- (ego y?) 35))
				else
					(switch (Random 1 2)
						(1
							(= egoX (- (ego nsLeft?) (Random 1 5)))
						)
						(2
							(= egoX (+ (ego nsRight?) (Random 1 5)))
						)
					)
					(switch (Random 1 2)
						(1
							(= egoY (- (ego nsTop?) (Random 1 5)))
						)
						(2
							(= egoY (+ (ego nsBottom?) (Random 1 5)))
						)
					)
				)
				((client blastID?) init:)
				(if (OneOf (client loop?) 0 2 4)
					((client blastID?) setLoop: 1)
				else
					((client blastID?) setLoop: 2)
				)
				((client blastID?)
					ignoreActors: 1
					view: 479
					posn: egoX egoY
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				((client blastID?) dispose:)
				(= clientX (client x?))
				(if (and register (not (ego script?)))
					(curRoom setScript: (ScriptID 707 1))
				)
				(= seconds 2)
			)
			(4 (self dispose:))
		)
	)
)

(instance firePulsar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if theSarienGuard
					(= theSarienGuardX (theSarienGuard x?))
					(= local162 (- (theSarienGuard y?) 30))
				else
					(= theSarienGuardX ((user curEvent?) x?))
					(= local162 ((user curEvent?) y?))
				)
				(if theSarienGuard (theSarienGuard setMotion: 0))
				(Face ego theSarienGuardX local162 self)
			)
			(1
				(cond 
					((== (DeltaurRegion egoStatus?) 1)
						(if (OneOf (ego loop?) 0 4 6 3)
							(ego setLoop: 6)
						else
							(ego setLoop: 7)
						)
					)
					((> (ego loop?) 3) (ego setLoop: (- (ego loop?) 2)))
					(else
						(switch (ego loop?)
							(2
								(if (> (ego x?) theSarienGuardX) (ego setLoop: 3))
							)
							(3
								(if (> (ego x?) theSarienGuardX)
									(ego setLoop: 5)
								else
									(ego setLoop: 4)
								)
							)
						)
					)
				)
				(ego view: 41 cel: 0 setCycle: CycleTo 1 1 self)
			)
			(2
				(soundFx number: 312 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(3
				(if (not theSarienGuard)
					(if (OneOf (ego loop?) 0 2 4)
						(blast setLoop: 1)
					else
						(blast setLoop: 2)
					)
					(blast
						init:
						startUpd:
						ignoreActors: 1
						view: 479
						posn: theSarienGuardX local162
						cel: 0
						setCycle: EndLoop self
					)
				else
					(self cue:)
				)
			)
			(4
				(blast dispose:)
				(if theSarienGuard
					(theSarienGuard setScript: gd1 self)
				else
					(self cue:)
				)
			)
			(5
				(= register (ego loop?))
				(EgoStatusCheck)
				(ego loop: register)
				(= theSarienGuard 0)
				(= ticks 18)
			)
			(6 (HandsOn) (self dispose:))
		)
	)
)

(instance gd1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SolvePuzzle 3 fKillDeltaurGuard)
				(client
					looper: 0
					setCycle: 0
					setLoop: (proc703_2 client)
					setMotion: 0
				)
				(= register 0)
				(if (IsObject (client blastID?))
					((client blastID?) dispose:)
				)
				(client view: 419 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(1
				(soundFx number: 368 loop: 1 play:)
				(client setCycle: RangeOsc 2 3 4 self)
			)
			(2
				(client setCycle: RangeOsc 2 5 6 self)
			)
			(3
				(if (theSarienGuard regionPathID?)
					(client pushToNextRoom: init: activate:)
				else
					(client dead: 1)
					(client dispose:)
				)
				(= ticks 18)
			)
			(4 (self dispose:))
		)
	)
)

(instance countDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DeltaurRegion
					timeToBlow: (- (DeltaurRegion timeToBlow?) 1)
				)
				(if (== (DeltaurRegion timeToBlow?) 20) (Print 703 50))
				(if (> (DeltaurRegion timeToBlow?) 0) (-- state))
				(= seconds 1)
			)
			(1
				(Bset fDeltaurSelfDestructs)
				(curRoom newRoom: 71)
			)
		)
	)
)

(instance saluteScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((client _head?) view: 66 setLoop: 8 cel: 0)
				(client lastLoop: (client loop?))
				(if (< (ego x?) (client x?))
					(client loop: 1)
				else
					(client loop: 0)
				)
				(client
					view: 69
					show:
					guardSalutes: TRUE
					setMotion: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1 (= ticks 300))
			(2
				(client activate:)
				(self dispose:)
			)
		)
	)
)

(instance pipes of RegionFeature
	(properties
		description {pipes}
		onMeCheck $0080
		level 3
	)
	
	(method (init)
		(= lookStr 0)
		(super init: &rest)
	)
	
	(method (doVerb)
		(if (not lookStr)
			(= lookStr
				{Like everything around here, the pipes are extremely colorful. It makes you wonder if the Sariens got a good deal on paint.}
			)
		else
			(= lookStr {Yep. More pipes.})
		)
		(super doVerb: &rest)
	)
)

(instance elevators of RegionFeature
	(properties
		description {elevator}
		onMeCheck $0100
		lookStr {An elevator shaft runs up through the ceiling and down through the floor.}
		level 3
	)
)

(instance tubes of RegionFeature
	(properties
		description {access tubes}
		onMeCheck $0200
		lookStr {This is a hallway access tube leading to another part of the Deltaur.}
		level 3
	)
)

(instance upperLevel of RegionFeature
	(properties
		description {upper level}
		onMeCheck $0010
		lookStr {You are on the upper level of one of the hallways on the Deltaur.}
		level 1
	)
)

(instance lowerLevel of RegionFeature
	(properties
		description {lower level}
		onMeCheck $0020
		lookStr {You are on the lower level of one of the hallways on the Deltaur.}
		level 2
	)
)

(instance sarienShot of Sound
	(properties
		number 312
	)
)
