;;; Sierra Script 1.0 - (do not remove this comment)
(script# 28)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use PChase)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Jump)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm28 0
)

(local
	oratAlive
	partOnFloor
	toX
	toY
	local4
	local5
	local6
	gEgoCel
	local8
	[oratDeadPts 46] = [0 0 319 0 319 189 247 189 319 139 295 121 269 123 256 108 218 106 200 98 162 93 129 95 91 105 71 116 110 122 105 132 56 133 88 146 75 156 21 159 11 153 24 142 0 148]
	[oratAlivePts 28] = [0 0 319 0 319 189 128 189 188 122 110 122 105 132 56 133 88 146 75 156 21 159 11 153 24 142 0 148]
)
(instance rm28 of SQRoom
	(properties
		picture 28
		south 24
		west 24
	)
	
	(method (init)
		(= currentFloor 2)
		(if (= oratAlive (not (Btst fOratDead)))
			(roomPoly points: @oratAlivePts size: 14)
		else
			(roomPoly points: @oratDeadPts size: 23)
		)
		(self addObstacle: roomPoly)
		(LoadMany VIEW 128 10 19)
		(Load SOUND 452)
		(cond 
			(oratAlive
				(orat init: stopUpd:)
				(LoadMany VIEW 424)
				(LoadMany SOUND 420)
				(if spiderRoom
					(LoadMany VIEW 422 328)
					(Load SOUND 411)
				)
			)
			((= partOnFloor (if (not (Btst fGotOratPart)) (Btst fOratDead))) (part init:))
		)
		(ego init:)
		(self setScript: intoCave)
		(super init:)
		(skullHoles init:)
		(farWall init:)
		(nearWall init:)
		(skulls init:)
		(bigRock init:)
		(smallRock init:)
		(floor init:)
		(theMusic
			number: (if (Btst fOratDead) 420 else 415)
			loop: -1
			play:
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (Btst fOratDead) (Print 28 0) else (Print 28 1))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom)
		(theMusic fade:)
		(super newRoom: &rest)
	)
)

(instance intoCave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: -9 203 setMotion: MoveTo 51 175 self)
			)
			(1
				(if oratAlive (ego setScript: noticeEgo))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoHides of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 11 150 self)
			)
			(1
				(ego
					view: 19
					setLoop: 1
					cel: 0
					posn: 38 147
					cycleSpeed: 16
					setCycle: EndLoop self
				)
			)
			(2
				(ego stopUpd:)
				(if (and spiderRoom oratAlive)
					((ScriptID 704 3)
						init:
						view: 422
						setCycle: Walk
						setLoop: 0
						z: 0
						posn: -30 203
						setMotion: MoveTo 72 168 self
					)
					(= thirstTimer 3000)
				else
					(ego setScript: 0)
					(curRoom setScript: egoReadyToComeOut)
				)
			)
			(3
				(orat setLoop: 3 forceUpd:)
				((ScriptID 704 3) setMotion: MoveTo 130 130 self)
				(Print 28 2)
			)
			(4
				(= local5 1)
				(orat cycleSpeed: 15 cel: 0 setCycle: Oscillate 1)
				((ScriptID 704 3) setMotion: MoveTo 180 118 self)
			)
			(5
				(= local5 0)
				(orat setLoop: 2 cel: 0 cycleSpeed: 20 setCycle: EndLoop self)
			)
			(6
				((ScriptID KERONA 3)
					view: 328
					setLoop: 0
					cel: 0
					setPri: (+ (orat priority?) 1)
					cycleSpeed: 15
					setCycle: CycleTo 1 1 self
				)
				(soundFx number: 411 loop: 1 play:)
			)
			(7
				(orat hide:)
				((ScriptID 704 3) cycleSpeed: 12 setCycle: EndLoop self)
			)
			(8
				(theMusic fade:)
				(orat
					show:
					setLoop: 4
					cel: 6
					cycleSpeed: 10
					setCycle: CycleTo 8 1 self
				)
				(Bclr fSpiderLanded)
				(= spiderRoom 0)
				(SolvePuzzle 5 fKillSpider)
				((ScriptID KERONA 3) dispose:)
			)
			(9
				(soundFx number: 419 loop: 1 play:)
				(orat setCycle: EndLoop self)
			)
			(10
				(theMusic number: 420 loop: -1 play: 0 fade: 127 25 10 0)
				(orat dispose:)
				(part init:)
				(= oratAlive FALSE)
				(= partOnFloor TRUE)
				(Bset fOratDead)
				(SolvePuzzle 5 fKillOrat)
				(ego setCycle: BegLoop self)
			)
			(11
				(NormalEgo 0 1 61)
				(ego setMotion: MoveTo 109 130 self)
			)
			(12
				(Print 28 3)
				(roomPoly points: @oratDeadPts size: 23)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoReadyToComeOut of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(HandsOn)
		(User canControl: FALSE)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= local4 0))
			(1
				(HandsOff)
				(ego
					posn: 19 147
					view: 19
					setLoop: 2
					cel: 0
					cycleSpeed: 30
					setCycle: EndLoop self
				)
			)
			(2
				(if local4
					(NormalEgo 0 1 61)
					(ego setMotion: PolyPath toX toY self)
				else
					(self cue:)
				)
			)
			(3
				(NormalEgo 0 1 61)
				(HandsOn)
				(= local4 0)
				(if (and oratAlive (!= next egoThrowsWater))
					(ego setScript: noticeEgo)
				)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event &rest))
			(
				(and
					(== (theIconBar curIcon?) (theIconBar at: ICON_WALK))
					(or
						(== (event message?) dirN)
						(& (event type?) direction)
					)
				)
				(if (== (event message?) dirN)
					(= local4 1)
					(if (OnControl 4 (event x?) (event y?))
						(= toX 162)
					else
						(= toX (event x?))
					)
					(= toY (event y?))
				)
				(event claimed: 1)
				(self cue:)
			)
		)
	)
)

(instance egoThrowsWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(water init: hide:)
				(= thirstTimer 11000)
				(orat
					cycleSpeed: 8
					view: 424
					setLoop: 3
					cel: 0
					setCycle: CycleTo Oscillate
				)
				(ego
					cycleSpeed: 8
					view: 19
					setLoop: 0
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(1
				(water
					show:
					cycleSpeed: 4
					posn: (+ (ego x?) 54) (- (ego y?) 34)
				)
				(orat cycleSpeed: 12 cel: 1 setCycle: CycleTo 2 1)
				(= cycles 2)
			)
			(2
				(water
					cycleSpeed: 18
					moveSpeed: 11
					setCycle: Forward
					setMotion: JumpTo (- (orat x?) 8) (- (orat y?) 48) self
				)
				(ego setCycle: EndLoop put: iDehydratedWater)
			)
			(3
				(NormalEgo 0 1 61)
				(ego loop: 6 heading: 45)
				(orat setCycle: EndLoop self)
				(soundFx number: 417 loop: 1 play:)
				(water dispose:)
			)
			(4
				(theMusic fade:)
				(orat
					setLoop: 4
					cycleSpeed: 12
					cel: 0
					setCycle: CycleTo 3 1 self
				)
				(Print 28 4)
			)
			(5
				(theMusic2 number: 418 loop: 1 play:)
				(orat setCycle: CycleTo 8 1 self)
			)
			(6
				(soundFx number: 419 loop: 1 play:)
				(orat setCycle: EndLoop self)
			)
			(7
				(theMusic number: 420 loop: -1 play: 0 fade: 127 25 10 0)
				(orat dispose:)
				(part init:)
				(= oratAlive FALSE)
				(= partOnFloor TRUE)
				(Bset fOratDead)
				(SolvePuzzle 5 fKillOrat)
				(roomPoly points: @oratDeadPts size: 23)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance noticeEgo of Script
	(properties)
	
	(method (doit)
		(if
		(and local6 (== (ego cel?) 2) (!= gEgoCel (ego cel?)))
			(soundFx number: 452 loop: 1 play:)
		)
		(cond 
			(local4 0)
			(
				(and
					(& (ego onControl: origin) cYELLOW)
					(not local8)
					(not local4)
				)
				(if state
					(Print 28 5)
					(HandsOff)
					(= local8 1)
					(ego setMotion: MoveTo (ego x?) 200)
				else
					(ego setScript: egoHides)
				)
			)
			(
				(and
					(<= state 1)
					(!= (ego view?) 19)
					(<= (ego y?) 148)
				)
				(= state 1)
				(self cue:)
			)
		)
		(super doit: &rest)
		(= gEgoCel (ego cel?))
	)
	
	(method (dispose)
		(= local5 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(= local5 1)
				(orat
					setLoop: 3
					cel: 0
					cycleSpeed: 18
					setCycle: Oscillate 2 self
				)
			)
			(2
				(= local5 0)
				(if (< (ego y?) 190)
					(ego setMotion: 0)
					(orat
						setLoop: 0
						cycleSpeed: 2
						moveSpeed: 2
						setCycle: Walk
						setMotion: PChase ego 30 self
					)
				)
			)
			(3
				(HandsOff)
				(Face ego orat self)
			)
			(4
				(Print 28 6)
				(orat setMotion: MoveTo (+ (ego x?) 18) (ego y?) self)
			)
			(5
				(ego hide:)
				(orat
					ignoreActors: 1
					view: 19
					setLoop: 3
					cel: 0
					cycleSpeed: 18
					setCycle: CycleTo 7 1 self
				)
			)
			(6
				(soundFx number: 453 loop: 1 play:)
				(orat cel: 8 setCycle: EndLoop self)
			)
			(7
				(soundFx stop:)
				(= local6 1)
				(orat setLoop: 4 stopUpd:)
				(ego
					ignoreActors: 1
					show:
					posn: (- (orat x?) 20) (orat y?)
					view: 19
					setLoop: 5
					cel: 0
					cycleSpeed: 18
					setCycle: Forward
				)
				(= seconds 4)
			)
			(8
				(= local6 1)
				(Print 28 7)
				(EgoDead 19 6 0 28 8)
			)
		)
	)
)

(instance orat of Actor
	(properties
		x 214
		y 113
		description {orat}
		lookStr {Orat is huge and ugly. Of course, your opinion may differ depending on what part of the universe you come from. You also get the impression that he might be quite mean.}
		yStep 4
		view 424
		loop 1
		signal ignrAct
		cycleSpeed 4
		xStep 6
		moveSpeed 4
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Walk)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== view 424)
				(== loop 3)
				(== cel 2)
				local5
				(!= gEgoCel cel)
			)
			(soundFx number: 416 loop: 1 play:)
		)
		(= gEgoCel cel)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (== (ego view?) 19)
					(egoReadyToComeOut start: 1)
					(= local4 1)
					(= toX x)
					(= toY y)
					(ego setScript: 0)
					(curRoom setScript: egoReadyToComeOut)
				else
					(HandsOff)
					(ego setMotion: PolyPath (orat x?) (orat y?))
				)
			)
			(verbUse
				(switch theItem
					(iDehydratedWater
						(cond 
							(
								(and
									(== (ego script?) noticeEgo)
									(>= (noticeEgo state?) 2)
								)
								(Print 28 9)
							)
							((== (curRoom script?) egoReadyToComeOut)
								(= local4 1)
								(= toX 120)
								(= toY 160)
								(ego setScript: 0)
								(egoReadyToComeOut next: egoThrowsWater cue:)
							)
							(else (ego setScript: egoThrowsWater))
						)
					)
					(iKnife
						(Print 28 10)
					)
					(iBuckazoid
						(Print 28 11)
					)
					(iCartridge
						(Print 28 12)
					)
					(iWidget
						(Print 28 13)
					)
					(iGadget
						(Print 28 14)
					)
					(iSurvivalKit
						(Print 28 15)
					)
					(iPlant
						(Print 28 16)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(verbTalk
				(Print 28 17)
			)
			(verbTaste
				(Print 28 18)
			)
			(verbSmell
				(Print 28 19)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance water of Actor
	(properties
		yStep 9
		view 128
		loop 1
		priority 9
		signal (| ignrAct fixedLoop fixPriOn)
		xStep 6
	)
)

(instance waterMover of Jump
	(properties
		gx -1
	)
)

(instance part of View
	(properties
		x 179
		y 117
		description {part of the orat}
		approachX 167
		approachY 127
		lookStr {This gleaming chunk of Orat's anatomy is the only visible proof that Orat ever existed. After that explosion, it's amazing there's that much left.}
		view 128
		priority 1
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(self approachVerbs: verbDo)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(ego setScript: pickUpPart)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pickUpPart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 0 self)
			)
			(1
				(ego
					view: 10
					setLoop: 2
					cel: 0
					cycleSpeed: 6
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(part dispose:)
				(SolvePuzzle 2 fTakeOratPart)
				(ego get: iOratPart)
				(Bset fGotOratPart)
				(= partOnFloor FALSE)
				(ego setCycle: EndLoop self)
			)
			(3
				(HandsOn)
				(NormalEgo 0 1 61)
				(ego loop: 3)
				(Print 28 20)
				(if (== bridgeCrossings 2) (= bridgeCrossings 1))
				(self dispose:)
			)
		)
	)
)

(instance skullHoles of Feature
	(properties
		description {holes}
		onMeCheck cLRED
		lookStr {The orbs in this gigantic skull offer the only passageways for light into this gloomy place.}
	)
)

(instance farWall of Feature
	(properties
		description {far wall}
		onMeCheck $0800
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (Btst fOratDead)
					(Print 28 21)
				else
					(Print 28 22)
				)
			)
			(verbDo
				(Print 28 23)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance nearWall of Feature
	(properties
		x 190
		y 200
		description {near wall}
		onMeCheck $0400
	)
	
	(method (doVerb theVerb theItem)
		(farWall doVerb: theVerb &rest)
	)
)

(instance skulls of Feature
	(properties
		x 278
		y 118
		description {pile of skulls}
		onMeCheck $0200
		lookStr {A pile of skulls lie here sucked clean by Orat, or was a result of them writing too many adventure games.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 28 24)
			)
			(verbTalk
				(Print 28 25)
			)
			(verbTaste
				(Print 28 26)
			)
			(verbSmell
				(Print 28 27)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bigRock of Feature
	(properties
		description {big rock}
		onMeCheck $0100
		lookStr {That rock is almost as big as you.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 28 28)
			)
			(verbSmell
				(Print 28 29)
			)
			(verbTaste
				(Print 28 30)
			)
			(verbTalk
				(Print 28 31)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance smallRock of Feature
	(properties
		description {small rock}
		onMeCheck $0080
		lookStr {That rock is too small to hide behind but too large to lift.}
	)
	
	(method (doVerb theVerb theItem)
		(bigRock doVerb: theVerb &rest)
	)
)

(instance floor of Feature
	(properties
		description {cave floor}
		onMeCheck $0080
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Printf
					{The cave floor is littered with the usual stuff a cave would be littered with. Some sand, some rock, %s}
					(if (= partOnFloor (not (Btst fGotOratPart)))
						{and that's about it. Oh, Yeah. There's also an Orat part.}
					else
						{none of it useful, though.}
					)
				)
			)
			(verbSmell
				(Print 28 32)
			)
			(verbTaste
				(Print 28 33)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance roomPoly of Polygon
	(properties
		type PBarredAccess
	)
)
