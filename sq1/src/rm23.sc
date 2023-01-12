;;; Sierra Script 1.0 - (do not remove this comment)
(script# 23)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Polygon)
(use MoveFwd)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm23 0
)

(instance rm23 of SQRoom
	(properties
		lookStr {This appears to be the thoracic area of the spinal column of this meatless beast. Other than an obviously defective vertebra, you've got more sand and crud.}
		picture 23
		horizon 20
		north 20
		east 24
		south 26
		west 22
		walkOffTop 1
	)
	
	(method (init)
		(if (== currentFloor 2)
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 0 189 0 172 7 165 26 169 6 189
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							96 189 105 181 158 174 171 179 257 163 245 146
							211 140 206 132 258 113 281 80 319 75 319 189
						yourself:
					)
			)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							319 78 289 68 291 57 281 59 260 53 248 46 251 42
							229 40 227 43 224 45 170 45 133 51 116 50 107 45
							37 57 25 63 0 63 0 0 319 0
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 319 189 0 189 0 74 47 71 88 60 155 59 221 58 272 69 319 102
						yourself:
					)
			)
			(LoadMany VIEW 14 123 423 328)
			(LoadMany SOUND 408 407)
		)
		(switch prevRoomNum
			(east (= style 11) (HandsOn))
			(west (= style SCROLLLEFT) (HandsOn))
			(south (= style SCROLLDOWN) (HandsOn))
			(north (= style SCROLLUP) (HandsOn))
			(else  (= style FADEOUT))
		)
		(ego init:)
		(self setRegions: KERONA)
		(super init:)
		(bridge init: stopUpd:)
		(breaks init: cel: bridgeCrossings stopUpd:)
		(if (Btst fPushedPodOffSkeleton)
			(pod setPri: 0 loop: 2 cel: 1 posn: 91 170)
		)
		(if (not (Btst fSpiderDead)) (pod init: stopUpd:))
	)
	
	(method (doit)
		(cond 
			(script 0)
			(
			(and (== currentFloor 1) (ego inRect: 61 52 68 72)) (self setScript: bridgeBreaks))
		)
		(super doit:)
	)
)

(instance bridgeBreaks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bridge startUpd:)
				(soundFx number: 408 loop: 1 play:)
				(breaks startUpd: cel: (++ bridgeCrossings))
				(= cycles 6)
			)
			(1
				(= register (ego loop?))
				(ego
					view: 14
					setLoop:
						(if
						(and (< 180 (ego heading?)) (< (ego heading?) 360))
							1
						else
							2
						)
					cel: 0
					setCycle: CycleTo 3 1
					setMotion: MoveFwd 14
				)
				(bridge y: 59)
				(breaks y: 59)
				(ego cel: 2)
				(= cycles 3)
			)
			(2
				(bridge y: 57)
				(breaks y: 57)
				(ego cel: 3)
				(= cycles 3)
			)
			(3
				(bridge y: 58)
				(breaks y: 58)
				(ego cel: 1)
				(= cycles 3)
			)
			(4
				(bridge y: 57)
				(breaks y: 57)
				(ego cel: 2)
				(= cycles 6)
			)
			(5
				(if (== bridgeCrossings 3)
					(client setScript: bridgeFalls)
				else
					(bridge stopUpd:)
					(breaks stopUpd:)
					(NormalEgo 0 1 61)
					(ego setPri: 14 loop: register)
					(ego setMotion: MoveFwd 32 self)
				)
			)
			(6
				(switch bridgeCrossings
					(1 (Print 23 0))
					(2 (Print 23 1))
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance bridgeFalls of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(breaks dispose:)
				(soundFx number: 363 loop: 1 play:)
				(bridge
					setLoop: 4
					setCycle: EndLoop
					setMotion: MoveTo 67 101 bridge
				)
				(ego
					cel: 0
					setStep: 5 15
					setCycle: EndLoop
					setMotion: MoveTo 68 189 self
				)
			)
			(1
				(soundFx number: 407 loop: 1 play:)
				(ego setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(2 (EgoDead 948 0 0 23 2))
		)
	)
)

(instance egoPushesPod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					posn: 97 62
					view: 14
					setLoop: 0
					cel: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(1
				(pod hide:)
				(ego setCycle: CycleTo 6 1 self)
			)
			(2
				(pod show: setLoop: 1 cel: 0 setCycle: Forward posn: 85 101)
				(ego setCycle: EndLoop)
				(if
					(and
						((ScriptID 704 3) inThisRoom:)
						((ScriptID 704 3) inRect: 70 160 120 205)
					)
					(client setScript: splatSpider)
				else
					(pod setMotion: MoveTo 91 170 self)
				)
			)
			(3
				(soundFx number: 407 loop: 1 play:)
				(NormalEgo 0 1 61)
				(ego loop: 2 setPri: 14 heading: 180)
				(pod
					ignoreActors:
					setPri: 0
					cel: 0
					setLoop: 2
					setCycle: EndLoop self
				)
			)
			(4
				(pod stopUpd:)
				(Bset fPushedPodOffSkeleton)
				(Print 23 3)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance splatSpider of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 704 3) setMotion: MoveTo 94 183)
				(pod setMotion: MoveTo 94 125 self)
			)
			(1
				(soundFx number: 407 loop: 1 play:)
				(NormalEgo 0 1 61)
				(ego loop: 2 setPri: 14 heading: 180)
				(pod hide:)
				((ScriptID 704 3) dispose:)
				(spiderBody init: stopUpd:)
				(spiderLegs init: setCycle: Forward)
				(= seconds 4)
			)
			(2
				(soundFx number: 411 loop: 1 play:)
				(spiderLegs
					cycleSpeed: 30
					view: 328
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
				(spiderBody dispose:)
			)
			(3
				(theMusic fade:)
				(Bset fSpiderDead)
				(Bclr fSpiderLanded)
				(SolvePuzzle 5 fKillSpider)
				(= spiderRoom 0)
				(spiderLegs dispose:)
				(Print 23 4)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance bridge of Actor
	(properties
		x 59
		y 57
		description {bone bridge}
		lookStr {This genetically defective vertebra looks like it would be intolerant to much load bearing.}
		yStep 14
		view 123
		cel 2
		priority 13
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 4
		xStep 5
		moveSpeed 4
	)
	
	(method (cue)
		(theMusic2 number: 412 loop: 1 play:)
	)
)

(instance breaks of Prop
	(properties
		x 59
		y 58
		z 1
		description {breaks}
		lookStr {What started out at one time as mere hairline fractures are now clearly cracks in the imperfect vertebral module.}
		view 123
		loop 3
		priority 13
		signal (| ignrAct fixPriOn)
		cycleSpeed 4
	)
)

(instance pod of Actor
	(properties
		x 97
		y 62
		description {strange plant pod}
		approachX 97
		approachY 60
		yStep 6
		view 123
		priority 15
		signal fixPriOn
		cycleSpeed 6
		moveSpeed 2
	)
	
	(method (init)
		(super init: &rest)
		(cond 
			((and (== currentFloor 1) (not (Btst fPushedPodOffSkeleton)))
				(self approachVerbs: verbDo verbSmell)
				(= approachX 97)
				(= approachY 63)
			)
			((and (== currentFloor 2) (Btst fPushedPodOffSkeleton))
				(self approachVerbs: verbSmell)
				(= approachX 101)
				(= approachY 153)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (Btst fPushedPodOffSkeleton) (Print 23 5) else (Print 23 6))
			)
			(verbDo
				(cond 
					((Btst fPushedPodOffSkeleton) (Print 23 7))
					((!= currentFloor 1) (Print 23 8))
					(else (self approachVerbs:) (curRoom setScript: egoPushesPod))
				)
			)
			(verbSmell
				(cond 
					((Btst fPushedPodOffSkeleton)
						(if (== currentFloor 1)
							(Print 23 9)
						else
							(Print 23 10)
						)
					)
					((!= currentFloor 1) (Print 23 11))
					(else (Print 23 12))
				)
			)
			(verbTaste
				(Print 23 13)
			)
			(verbTalk
				(Print 23 14)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance spiderBody of View
	(properties
		x 95
		y 182
		view 423
		loop 4
	)
)

(instance spiderLegs of Prop
	(properties
		x 95
		y 182
		view 423
		loop 5
		cel 3
		signal ignrAct
		cycleSpeed 3
	)
)
