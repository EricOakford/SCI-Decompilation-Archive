;;; Sierra Script 1.0 - (do not remove this comment)
(script# 58)
(include game.sh)
(use Main)
(use Intrface)
(use Deltaur)
(use SQRoom)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm58 0
)

(local
	behindCounter
	local1
	warnCount
	local3
	saveBits
	talkCount
	theCel
	local7
	[openPts 38] = [33 160 12 172 28 183 63 171 85 187 117 185 93 171 93 140 59 124 95 105 123 121 125 144 152 127 95 94 53 117 46 111 36 116 41 126 21 140]
	[closedPts 20] = [33 160 12 172 28 183 63 171 85 187 117 185 93 171 93 140 50 116 21 140]
)
(procedure (localproc_000e &tmp temp0 temp1 temp2 temp3)
	(= temp0 (+ (ego x?) 11))
	(= temp1 (- (ego y?) 30))
	(if (& (ego onControl: 0) $0008)
		(= temp2 (- (robot x?) 29))
		(= temp3 (- (robot y?) 15))
	else
		(= temp2 (- (robot x?) 33))
		(= temp3 (- (robot y?) 32))
	)
	(if (< temp1 temp3) (= temp1 temp3))
	(Graph GDrawLine temp1 temp0 temp3 temp2 12 15 -1)
	(Graph GShowBits 86 0 189 209 1)
)

(instance rm58 of SQRoom
	(properties
		lookStr {Rows of weapons on the wall tell you that you are in the Deltaur's Weapons Dispensary. There is a service droid behind the weapons counter.}
		picture 58
		style WIPERIGHT
	)
	
	(method (init)
		(self setRegions: DELTAUR)
		(= currentFloor 2)
		(LoadMany VIEW 158 40 91 460 519 76 67 461)
		(LoadMany TEXT 158)
		(LoadMany SOUND 524 523)
		(HandsOff)
		(= local1 1)
		(pOpenPoly points: @openPts size: 19)
		(pClosedPoly points: @closedPts size: 10)
		(self addObstacle: pClosedPoly)
		(features
			add: storeRoom shadows counterTop openning
			eachElementDo: #init
		)
		(robot init: setPri: 8)
		(if (not (ego has: 12))
			(DeltaurRegion timesShownID: 0)
		)
		(switch (DeltaurRegion numGrenades?)
			(1 (grenade2 init:))
			(2
				(grenade1 init:)
				(grenade2 init:)
			)
		)
		(ego posn: 1 189 init:)
		(panel init: stopUpd:)
		(super init: &rest)
		(self setScript: fromGeneratorRoom)
	)
	
	(method (doit)
		(cond 
			(
				(and
					(<= 68 (ego x?))
					(<= (ego x?) 159)
					(<= 83 (ego y?))
					(<= (ego y?) 119)
					(!= (ego priority?) 5)
				)
				(ego setPri: 5)
			)
			(
				(and
					(or
						(< (ego x?) 68)
						(> (ego x?) 159)
						(< (ego y?) 83)
						(> (ego y?) 119)
					)
					(& (ego signal?) $0010)
				)
				(ego setPri: -1)
			)
			((and (!= script bugEgo) script) 0)
			((and local1 behindCounter (not local3)) (HandsOff) (= local3 1))
			((& (ego onControl: 0) $0002) (self setScript: toGeneratorRoom))
			((& (ego onControl: 0) $0010)
				(if (robot script?) (robot setScript: 0))
				(self setScript: shootEgo1)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb theItem)
		(if (and (== theVerb 4) (== theItem iPulseray))
			(self setScript: warnEgo)
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance pOpenPoly of Polygon
	(properties
		type PContainedAccess
	)
)

(instance pClosedPoly of Polygon
	(properties
		type PContainedAccess
	)
)

(instance grenade1 of View
	(properties
		x 117
		y 84
		lookStr {A gas grenade.}
		view 158
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(if behindCounter
					(rm58 setScript: egoPickUpGrenade)
				else
					(Print 58 0)
				)
			)
			(verbLook
				(Print 58 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance grenade2 of View
	(properties
		x 125
		y 88
		lookStr {A gas grenade.}
		view 158
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(if behindCounter
					(rm58 setScript: egoPickUpGrenade)
				else
					(Print 58 0)
				)
			)
			(verbLook
				(Print 58 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance panel of Prop
	(properties
		x 58
		y 94
		nsTop 78
		nsLeft 48
		view 158
		loop 1
		priority 4
		signal (| ignrAct fixPriOn)
		cycleSpeed 9
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if behindCounter
					(rm58 setScript: egoWalksUnderOut)
				else
					(rm58 setScript: egoWalksUnderIn)
				)
			)
			(verbUse
				(if (== theItem iPulseray)
					(curRoom setScript: warnEgo)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(verbLook
				(Print 58 2)
			)
			(verbTaste
				(Print 58 3)
			)
			(verbSmell
				(Print 58 3)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance junk of Actor
	(properties
		view 158
		signal ignrAct
		cycleSpeed 5
		moveSpeed 3
	)
)

(instance robot of Actor
	(properties
		x 135
		y 164
		lookStr {A robot serves behins the counter. Its appearance is that of a plain and unintelligent droid. But then, looks can be deceiving as in your case.}
		yStep 4
		view 460
		loop 4
		cel 4
		cycleSpeed 15
		xStep 8
		moveSpeed 15
	)
	
	(method (init)
		(super init: &rest)
		(if (== (DeltaurRegion egoStatus?) 1)
			(self view: 460 loop: 4 cel: 4)
		else
			(self view: 461 setLoop: 1 cel: 2 setPri: 8)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== view 460)
				cycler
				local1
				(!= theCel cel)
				(or
					(and (<= loop 1) (OneOf cel 0 4))
					(and (>= 3 loop) (>= loop 2) (OneOf cel 3 7))
				)
			)
			(droidStepSound number: 524 loop: 1 play:)
		)
		(= theCel cel)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk
				(if (not script)
					(droidWalkSound number: 523 loop: -1 flags: 1 play:)
					(switch (if (< (++ talkCount) 4) talkCount else (= talkCount 4))
						(1
							(robotT
								init: robotBust robotMouth robotEyes 158 0 0 1 self
							)
						)
						(2
							(robotT
								init: robotBust robotMouth robotEyes 158 1 0 1 self
							)
						)
						(3
							(robotT
								init: robotBust robotMouth robotEyes 158 2 0 1 self
							)
						)
						(4
							(robotT
								init: robotBust robotMouth robotEyes 158 3 0 1 self
							)
						)
					)
				else
					(Print 58 4)
				)
			)
			(verbUse
				(cond 
					((not script)
						(switch theItem
							(iPulseray
								(curRoom setScript: warnEgo)
							)
							(iSarienIDCard
								(rm58 setScript: egoShowsCard)
							)
							(iCartridge
								(Print 58 5)
							)
							(else 
								(super doVerb: theVerb theItem &rest)
							)
						)
					)
					((== theItem iPulseray) (curRoom setScript: warnEgo))
					(else (Print 58 6))
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(droidWalkSound stop:)
	)
)

(instance storeRoom of RegionFeature
	(properties
		x 200
		y 54
		description {store room}
		onMeCheck $0200
		level 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch (DeltaurRegion numGrenades?)
					(0
						(Print 58 7)
					)
					(1
						(Print 58 8)
					)
					(2
						(Print 58 9)
					)
				)
			)
			(verbUse
				(if (== theItem iPulseray)
					(curRoom setScript: warnEgo)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance counterTop of RegionFeature
	(properties
		x 133
		y 170
		description {counter top}
		onMeCheck $0400
		lookStr {This is a long S-shaped counter top that runs from one end of the armory to the other. At one end there is an opening, probably used to allow access to the other side of counter.}
		level 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 58 10)
			)
			(verbLook
				(Print 58 11)
			)
			(verbTaste
				(Print 58 12)
			)
			(verbSmell
				(Print 58 13)
			)
			(verbUse
				(if (== theItem iPulseray)
					(curRoom setScript: warnEgo)
				else
					(Print 58 14)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance openning of Feature
	(properties
		x 74
		y 98
		description {counter top openning}
		onMeCheck $0800
		lookStr {This is an opening in the counter used to gain access behind the counter.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if behindCounter
					(rm58 setScript: egoWalksUnderOut)
				else
					(rm58 setScript: egoWalksUnderIn)
				)
			)
			(verbUse
				(if (== theItem iPulseray)
					(curRoom setScript: warnEgo)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(verbLook
				(Print 58 2)
			)
			(verbTaste
				(Print 58 15)
			)
			(verbSmell
				(Print 58 16)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
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
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Print 58 17)
			)
			(verbLook
				(Print 58 18)
			)
			(verbTaste
				(Print 58 17)
			)
			(verbSmell
				(Print 58 17)
			)
			(verbUse
				(if (== theItem iPulseray)
					(curRoom setScript: warnEgo)
				else
					(Print 58 19)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance robotT of Talker
	(properties
		x 10
		y 7
		nsTop 15
		nsLeft 228
		view 519
	)
)

(instance robotBust of View
	(properties
		view 519
		cel 1
	)
)

(instance robotMouth of Prop
	(properties
		nsTop 44
		nsLeft 19
		view 519
		loop 2
		cycleSpeed 12
	)
)

(instance robotEyes of Prop
	(properties
		nsTop 22
		nsLeft 7
		view 519
		loop 1
		cycleSpeed 30
	)
)

(instance fromGeneratorRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego loop: 6 setHeading: 45 setMotion: MoveTo 61 162 self)
			)
			(1
				(if (!= (DeltaurRegion egoStatus?) egoWithHelmet)
					(curRoom setScript: shootEgo2)
				else
					(= ticks 18)
				)
			)
			(2
				(droidWalkSound number: 523 loop: -1 flags: mNOPAUSE play:)
				(robotT
					init: robotBust robotMouth robotEyes 158 4 0 0 self
				)
			)
			(3
				(robotT
					init: robotBust robotMouth robotEyes 158 5 0 1 self
				)
			)
			(4
				(droidWalkSound stop:)
				(curRoom setScript: bugEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance shootEgo1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 90)
				(droidWalkSound number: 523 loop: -1 flags: 1 play:)
				(robot
					show:
					posn: 191 83
					view: 461
					setLoop: 0
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 241 118 self
				)
			)
			(1
				(droidWalkSound stop:)
				(junk
					init:
					cycleSpeed: 210
					posn: 239 75
					view: 461
					loop: 3
					setCycle: Forward
				)
				(robotHead init: setCycle: EndLoop)
				(if (& (ego onControl: 0) $0008)
					(robot setLoop: 2 cel: 0 setCycle: CycleTo 1 1 self)
				else
					(robot setLoop: 2 cel: 0 setCycle: CycleTo 2 1 self)
				)
			)
			(2
				(droidWalkSound number: 523 loop: -1 flags: 1 play:)
				(robotT
					init: robotBust robotMouth robotEyes 158 6 0 1 self
				)
			)
			(3
				(droidWalkSound stop:)
				(= saveBits (Graph GSaveBits 86 0 189 209 1))
				(localproc_000e)
				(= seconds 1)
			)
			(4
				(soundFx number: 312 loop: 1 play:)
				(robot setCycle: EndLoop self)
			)
			(5
				(soundFx number: 368 loop: 1 play:)
				(ego view: 67 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(6
				(Graph GRestoreBits saveBits)
				(Graph GShowBits 86 0 189 209 1)
				(= seconds 5)
			)
			(7 (EgoDead) (self dispose:))
		)
	)
)

(instance shootEgo2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 90 self)
			)
			(1
				(droidWalkSound number: 523 loop: -1 flags: 1 play:)
				(robot setCycle: Reverse setMotion: MoveTo 159 150 self)
			)
			(2
				(droidWalkSound stop:)
				(junk
					init:
					posn: (- (robot x?) 1) (- (robot y?) 42)
					cycleSpeed: 210
					view: 461
					loop: 3
					setCycle: Forward
				)
				(if (& (ego onControl: 0) cCYAN)
					(robot setLoop: 2 cel: 0 setCycle: CycleTo 1 1 self)
				else
					(robot setLoop: 2 cel: 0 setCycle: CycleTo 2 1 self)
				)
			)
			(3
				(droidWalkSound number: 523 loop: -1 flags: 1 play:)
				(robotT
					init: robotBust robotMouth robotEyes 158 7 0 1 self
				)
			)
			(4
				(droidWalkSound stop:)
				(= saveBits (Graph GSaveBits 86 0 189 209 1))
				(localproc_000e)
				(= seconds 2)
			)
			(5
				(soundFx number: 312 loop: 1 play:)
				(robot setCycle: EndLoop self)
			)
			(6
				(soundFx number: 368 loop: 1 play:)
				(ego view: 67 loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(7
				(Graph GRestoreBits saveBits)
				(Graph GShowBits 86 0 189 209 1)
				(= seconds 5)
			)
			(8 (EgoDead) (self dispose:))
		)
	)
)

(instance toGeneratorRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 1 189 self)
			)
			(1 (curRoom newRoom: 64))
		)
	)
)

(instance robotWalksToRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(droidWalkSound number: 523 loop: -1 flags: 1 play:)
				(robot
					setLoop: 2
					cel: 1
					cycleSpeed: 15
					moveSpeed: 15
					setCycle: Walk
					setMotion: MoveTo 241 118 self
				)
			)
			(1
				(robot
					setPri: 7
					setLoop: 3
					cel: 1
					setMotion: MoveTo 191 83 self
				)
			)
			(2
				(= local1 0)
				(droidWalkSound stop:)
				(robot hide:)
				(self dispose:)
			)
		)
	)
)

(instance robotGetWeapon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local1 1)
				(self setScript: robotWalksToRoom self)
			)
			(1
				(if local3
					(client setScript: shootEgo1)
				else
					(HandsOn)
					(= seconds 40)
				)
			)
			(2
				(if behindCounter
					(client setScript: shootEgo1)
				else
					(client setScript: robotWalksOut)
				)
				(self dispose:)
			)
		)
	)
)

(instance robotWalksOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(robotGetWeapon seconds: 0)
				(if (ego has: 12)
					(robot setLoop: 5)
				else
					(robot setLoop: 0)
				)
				(droidWalkSound number: 523 loop: -1 flags: 1 play:)
				(robot show: setMotion: MoveTo 241 118 self)
			)
			(1
				(= local1 1)
				(robot
					setPri: 8
					setLoop: (+ (robot loop?) 1)
					setMotion: MoveTo 135 164 self
				)
			)
			(2
				(droidWalkSound stop:)
				(if (or (!= (ego x?) 89) (!= (ego y?) 161))
					(ego setMotion: MoveTo 89 161 self)
				else
					(= ticks 18)
				)
			)
			(3
				(robot setLoop: 4 cel: 0 setCycle: 0)
				(cond 
					((ego has: 12)
						(droidWalkSound number: 523 loop: -1 flags: 1 play:)
						(robotT
							init: robotBust robotMouth robotEyes 158 8 0 1 self
						)
						(robot cel: 4)
					)
					((not (curRoom script?)) (client setScript: robotGiveWeapon))
				)
			)
			(4
				(droidWalkSound stop:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance dropAnvil of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(robot setScript: 0)
				(ego setScript: 0)
				(HandsOff)
				(if (& (ego onControl: 0) $0020)
					(ego setMotion: PolyPath 59 141 self)
				else
					(= ticks 18)
				)
			)
			(1
				(ego
					view: 76
					cel: 0
					loop: 1
					cycleSpeed: 2
					setCycle: CycleTo 3 1
				)
				(junk
					init:
					setPri: 15
					posn: (- (ego x?) 10) -10
					view: 76
					setLoop: 3
					setCel: 0
					yStep: 12
					cycleSpeed: 2
					moveSpeed: 2
					setMotion: MoveTo (- (ego x?) 10) (- (ego y?) 27) self
				)
			)
			(2
				(ego setCel: 4)
				(= ticks 12)
			)
			(3
				(junk dispose:)
				(ego setCycle: EndLoop self)
			)
			(4 (= seconds 5))
			(5 (EgoDead) (self dispose:))
		)
	)
)

(instance robotGiveWeapon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego get: 12 view: 40 loop: 5 setCel: 0)
				(SolvePuzzle 1 f58GetWeapon)
				(robot cycleSpeed: 6 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(1
				(ego setCel: 2)
				(robot setCel: 3)
				(= ticks 50)
			)
			(2
				(ego setCycle: EndLoop)
				(robot setCycle: EndLoop self)
			)
			(3
				(EgoStatusCheck)
				(ego loop: 6)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoShowsCard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 89 161 self)
			)
			(1
				(ego view: 91 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(2
				(droidWalkSound number: 523 loop: -1 flags: 1 play:)
				(if
					(<
						(DeltaurRegion
							timesShownID: (+ (DeltaurRegion timesShownID?) 1)
						)
						3
					)
					(robotT
						init:
							robotBust
							robotMouth
							robotEyes
							158
							(+ 8 (DeltaurRegion timesShownID?))
							0
							1
					)
					(robot setScript: robotGetWeapon)
					(ego setCycle: BegLoop self)
				else
					(robotT
						init: robotBust robotMouth robotEyes 158 11 0 1 robot
					)
					(curRoom setScript: dropAnvil)
					(self dispose:)
				)
			)
			(3
				(EgoStatusCheck)
				(ego loop: 6)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoWalksUnderIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= behindCounter 1)
				((curRoom obstacles?) delete: pClosedPoly)
				((curRoom obstacles?) add: pOpenPoly)
				(ego setMotion: PolyPath 63 128 self)
			)
			(1 (ego setHeading: 45 self))
			(2
				(soundFx number: 539 loop: 1 play:)
				(panel startUpd: setCycle: EndLoop self)
			)
			(3
				(if (robot script?)
					(HandsOn)
					(panel stopUpd:)
					(self dispose:)
				else
					(droidWalkSound number: 523 loop: -1 flags: 1 play:)
					(robotT
						init: robotBust robotMouth robotEyes 158 12 0 1 self
					)
				)
			)
			(4
				(droidWalkSound stop:)
				(panel stopUpd:)
				(curRoom setScript: dropAnvil)
				(self dispose:)
			)
		)
	)
)

(instance egoWalksUnderOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= behindCounter FALSE)
				(ego setMotion: PolyPath 63 128 self)
			)
			(1
				(ego setPri: -1 setHeading: 45 self)
			)
			(2
				((curRoom obstacles?) delete: pOpenPoly)
				((curRoom obstacles?) add: pClosedPoly)
				(soundFx number: 540 loop: 1 play:)
				(panel startUpd: setCycle: BegLoop self)
			)
			(3
				(cond 
					((== (robot script?) robotGetWeapon) (robot setScript: robotWalksOut))
					((== (robot script?) robotWalksOut) (robot setScript: robotGiveWeapon))
				)
				(panel stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance egoPickUpGrenade of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego has: iGrenade)
					(Print 58 20)
					(HandsOn)
					(self dispose:)
				else
					(DeltaurRegion
						numGrenades: (- (DeltaurRegion numGrenades?) 1)
					)
					(switch (DeltaurRegion numGrenades?)
						(1
							(ego setMotion: PolyPath 96 98 self)
						)
						(0
							(ego setMotion: PolyPath 105 103 self)
						)
						(else  (Print 58 21))
					)
				)
			)
			(1
				(SolvePuzzle 1 fGetGrenade)
				(ego
					view: 40
					loop: 2
					cycleSpeed: 24
					cel: 0
					setCycle: CycleTo 1 1 self
				)
			)
			(2
				(switch (DeltaurRegion numGrenades?)
					(1 (grenade1 dispose:))
					(0 (grenade2 dispose:))
				)
				(ego setCycle: EndLoop self)
			)
			(3
				(ego get: iGrenade)
				(EgoStatusCheck)
				(ego setPri: 5 loop: 6)
				(= ticks 18)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance warnEgo of Script
	(properties)
	
	(method (dispose)
		(droidWalkSound stop:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self setScript: firePulsar self)
			)
			(1
				(droidWalkSound number: 523 loop: -1 flags: 1 play:)
				(switch (++ warnCount)
					(1
						(robotT
							init: robotBust robotMouth robotEyes 158 13 0 1 self
						)
						(HandsOn)
					)
					(2
						(robotT
							init: robotBust robotMouth robotEyes 158 14 0 1 self
						)
						(curRoom setScript: dropAnvil)
					)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance bugEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 20 30)))
			(1
				(droidWalkSound number: 523 loop: -1 flags: 1 play:)
				(robotT
					init: robotBust robotMouth robotEyes 158 (+ local7 15) 0 1 self
				)
			)
			(2
				(droidWalkSound stop:)
				(if (< (++ local7) 3)
					(self changeState: 0)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance firePulsar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face
					ego
					((user curEvent?) x?)
					((user curEvent?) y?)
					self
				)
			)
			(1
				(if (== (DeltaurRegion egoStatus?) egoWithHelmet)
					(if (OneOf (ego loop?) 0 4 6 3)
						(ego setLoop: 6)
					else
						(ego setLoop: 7)
					)
				)
				(ego view: 41 cel: 0 setCycle: CycleTo 1 1 self)
			)
			(2
				(soundFx number: 312 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(3
				(= register (ego priority?))
				(EgoStatusCheck)
				(ego setPri: register)
				(= ticks 18)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance droidWalkSound of Sound
	(properties)
)

(instance droidStepSound of Sound
	(properties)
)

(instance robotHead of Prop
	(properties
		view 461
		loop 3
		signal (| ignrAct ignrHrz)
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPri: (robot priority?)
			posn: (- (robot x?) 1) (- (robot y?) 42)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 58 22)
			)
			(verbLook
				(Print 58 23)
			)
			(verbTaste
				(Print 58 24)
			)
			(verbSmell
				(Print 58 24)
			)
			(verbUse
				(Print 58 24)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
