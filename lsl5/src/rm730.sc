;;; Sierra Script 1.0 - (do not remove this comment)
(script# 730)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use PrintD)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm730 0
)

(local
	local0
	local1
	roomState
	paidBouncer
	local4
	local5
)
(procedure (InitStage)
	(runway init:)
	(stage init:)
)

(procedure (DisposeStage)
	(runway dispose:)
	(stage dispose:)
)

(instance rm730 of LLRoom
	(properties
		lookStr {The Ballroom is pulsating with people--all of them men. "Where are the women?" you wonder.}
		picture 730
	)
	
	(method (init)
		(ego init:)
		(HandsOff)
		(switch prevRoomNum
			(710
				(self setScript: sFromSouth)
			)
			(else 
				(ego get: iSilverDollar)
				(= silverDollars 5000)
				(Bset fMetLana)
				(HandsOn)
				(self setScript: sFromSouth)
			)
		)
		(super init:)
		(bouncer init: approachVerbs: verbTalk verbUse)
		(InitStage)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						16 189
						16 183
						33 164
						0 164
					yourself:
				)
		)
		(theMusic number: 738 setLoop: -1 flags: mNOPAUSE play: 127)
		(cond 
			((Btst fMetLana)
				(LoadMany VIEW 1731 552)
				(LoadMany SOUND 744 743)
				(= roomState 1)
				(ring init: stopUpd:)
				(ropes init: stopUpd:)
			)
			((Btst fFlag5)
				(= roomState 0)
			)
			(else
				(= roomState 2)
				(Load SOUND 743)
				(LoadMany VIEW 733 738)
			)
		)
		(Load VIEW 731)
	)
	
	(method (doit)
		(super doit:)
		(if (and (ego mover?) (== (curRoom curPic?) 738))
			(ego setMotion: 0)
			(self doVerb:)
		)
		(cond 
			(script)
			((ego script?))
			((ego edgeHit?)
				(ego edgeHit: 0)
				(HandsOff)
				(curRoom setScript: sLeave)
			)
			((and (ego sitting?) (ego mover?))
				(if (== (curRoom curPic?) 738)
					(self doVerb:)
				else
					(HandsOff)
					(ego setScript: sStandUp)
				)
			)
			(
				(and
					(cast contains: lana)
					(not local1)
					(== (lana script?) sTaunt)
				)
				(ego setPri: 14 setScript: sLarryIntoRing)
			)
			((and (< (ego y?) 137) (not (ego sitting?))) (ego setScript: sSitDown))
		)
	)
	
	(method (doVerb theVerb theItem)
		(if (== (curRoom curPic?) 738)
			(cast eachElementDo: #show)
			(curRoom drawPic: 730 setScript: 0)
			(herEyes dispose:)
			(herMouth dispose:)
			(InFirstPerson 0)
			(InitStage)
			(sContest start: (sContest state?))
			(contestant setScript: sContest)
			1
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
	
	(method (newRoom n)
		(if (!= n 740)
			(theMusic fade:)
		)
		(super newRoom: n)
	)
)

(instance sLeave of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: MoveTo (- (ego x?) 20) (+ (ego y?) 25) self
				)
			)
			(1 (curRoom newRoom: 710))
		)
	)
)

(instance sFromSouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					normalize:
					posn: -30 222
					setMotion: PolyPath 8 188 self
				)
			)
			(1 (ego setHeading: 0 self))
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance sSitDown of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 731
					setLoop: 1
					setCel: 0
					setPri: 14
					setCycle: EndLoop
					setMotion: MoveTo 111 130 self
				)
			)
			(1
				(ego
					x: 111
					y: 123
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(ego sitting: 1)
				(HandsOn)
				(cond 
					((== roomState 1)
						(if local1 (= local4 0))
						(if (not (cast contains: lana))
							(lana init: setScript: sWrestle)
						)
					)
					((not (cast contains: contestant)) (contestant init: setScript: sContest))
				)
				(self dispose:)
			)
		)
	)
)

(instance sStandUp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego sitting: 0)
				(ego setCycle: BegLoop self)
			)
			(1
				(ego posn: (ego x?) (+ (ego y?) 7))
				(= cycles 1)
			)
			(2
				(ego
					setLoop: 2
					setCel: 0
					setCycle: EndLoop
					setMotion: PolyPath 80 137 self
				)
			)
			(3
				(HandsOn)
				(ego normalize:)
				(if
					(and
						((CueObj client?) approachX?)
						(!= ((theIconBar curIcon?) cursor?) 0)
					)
					(ego
						setMotion:
							PolyPath
							((CueObj client?) approachX?)
							(+ (ego z?) ((CueObj client?) approachY?))
							CueObj
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance sWrestle of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(lana
					setLoop: 0
					setCycle: Forward
					setMotion: MoveTo 265 82 self
				)
			)
			(1
				(lana setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(soundFX number: 743 play:)
				(lana setLoop: 2 setCel: 0 setCycle: CycleTo 7 1 self)
			)
			(3
				(robe init: setCycle: EndLoop)
				(lana
					setLoop: 4
					setCycle: Forward
					setMotion: MoveTo 223 82 self
				)
			)
			(4
				(lana
					x: 203
					y: 65
					setLoop: 5
					setCel: 0
					setPri: 7
					cycleSpeed: 4
					setCycle: EndLoop self
				)
			)
			(5
				(soundFX number: 744 play:)
				(lana setLoop: 4 x: 174 y: 82)
				(= cycles 2)
			)
			(6
				(lana cycleSpeed: 6 setScript: sTaunt)
				(self dispose:)
			)
		)
	)
)

(instance sContest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(TimePrint 730 0)
				(= seconds 3)
			)
			(1
				(contestant setCycle: Walk setMotion: MoveTo 250 82 self)
			)
			(2
				(soundFX number: 743 play:)
				(contestant setCycle: Walk setMotion: MoveTo 160 82 self)
			)
			(3
				(contestant
					setLoop: 2
					setCel: 0
					setMotion: 0
					cycleSpeed: 8
					moveSpeed: 8
					setCycle: EndLoop self
				)
			)
			(4
				(contestant setCycle: BegLoop self)
			)
			(5 (= seconds 2))
			(6
				(contestant
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo (- (contestant x?) 25) (contestant y?) self
				)
			)
			(7
				(contestant setLoop: 3 setCel: 255 setCycle: BegLoop self)
			)
			(8 (= seconds 2))
			(9
				(contestant
					setLoop: -1
					cycleSpeed: 6
					moveSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 360 82 self
				)
			)
			(10
				(TimePrint 730 1)
				(theMusic fade: 0 10 5 1)
				(self dispose:)
			)
		)
	)
)

(instance sLarryIntoRing of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (ego sitting?))
					(ego setMotion: PolyPath 70 132 self)
				else
					(= cycles 3)
				)
			)
			(1
				(if (not (ego sitting?))
					(ego
						view: 731
						setLoop: 1
						setCel: 0
						setPri: 14
						setCycle: EndLoop
						setMotion: MoveTo 101 130 self
					)
				else
					(= cycles 3)
				)
			)
			(2
				(ego
					view: 552
					sitting: 0
					setLoop: -1
					setCycle: Walk
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 90 123 self
				)
			)
			(3
				(ropes ignoreActors: TRUE)
				(ring ignoreActors: TRUE)
				(ego
					view: 552
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 109 102 self
				)
			)
			(4
				(ego view: 731 setCel: 0 setCycle: CycleTo 7 1 self)
			)
			(5
				(ego setPri: (+ (ring priority?) 1) setCycle: EndLoop self)
			)
			(6
				(soundFX number: 744 play: self)
			)
			(7 (curRoom newRoom: 740))
		)
	)
)

(instance sTaunt of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (lana x?) 184)
					(lana
						setLoop: 6
						setCycle: Forward
						setMotion: MoveTo 115 82 self
					)
				else
					(lana
						setLoop: 7
						setCycle: Forward
						setMotion: MoveTo 184 82 self
					)
				)
			)
			(1
				(lana setLoop: 8 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(switch (Random 0 5)
					(0 (Say Lana 730 2 #dispose))
					(1 (Say Lana 730 3 #dispose))
					(2 (Say Lana 730 4 #dispose))
					(3 (Say Lana 730 5 #dispose))
					(4 (Say Lana 730 6 #dispose))
					(5 (Say Lana 730 7 #dispose))
				)
				(StartTimer (Random 10 20) 1 self)
			)
			(3 (self changeState: 0))
		)
	)
)

(instance sCloseup of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 4)))
			(1
				(herEyes init: setCel: 0 setCycle: BegLoop self)
			)
			(2 (= seconds (Random 2 4)))
			(3
				(herMouth init: setCel: 0 setCycle: EndLoop self)
			)
			(4 (= seconds (Random 1 3)))
			(5
				(herMouth setCycle: BegLoop self)
			)
			(6 (= seconds (Random 2 4)))
			(7
				(herEyes init: setCel: 0 setCycle: BegLoop self)
			)
			(8
				(= start (* 2 (Random 0 1)))
				(self init:)
			)
		)
	)
)

(instance contestant of Actor
	(properties
		x 340
		y 82
		description {Jennifer Jiggle}
		view 733
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(SolvePuzzle 8 fLookAtJennifer)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 738 setScript: sCloseup)
				(InFirstPerson 1)
				(DisposeStage)
				(self setCycle: 0 setMotion: 0)
			)
			(verbTalk
				(Say ego 730 8)
			)
			(verbUse
				(TimePrint 730 9)
			)
			(verbZipper
				(TimePrint 730 10)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance lana of Actor
	(properties
		x 340
		y 82
		description {Lana Luscious}
		lookStr {Lana looks much different, now that she's out of her roller skates and into her element!}
		view 734
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 730 11)
			)
			(verbTalk
				(Say ego 730 12)
			)
			(verbZipper
				(TimePrint 730 13)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance robe of Prop
	(properties
		x 300
		y 55
		description {her robe}
		lookStr {She threw away her robe!}
		view 734
		loop 3
	)
)

(instance ring of View
	(properties
		x 147
		y 107
		description {the mud wrestling ring}
		lookStr {Welcome to "Tramp This! Mud Night Madness" mud wrestling ring.}
		view 730
		priority 4
		signal (| ignrAct fixPriOn)
	)
)

(instance ropes of View
	(properties
		x 147
		y 107
		description {the ropes}
		lookStr {Welcome to "Tramp This! Mud Night Madness" mud wrestling ring.}
		view 730
		cel 1
		priority 8
		signal (| ignrAct fixPriOn)
	)
)

(instance bouncer of Actor
	(properties
		x 16
		y 161
		description {the bouncer}
		sightAngle 40
		approachX 16
		approachY 168
		lookStr {You feel certain this bouncer is not nearly as wise as you in the ways of women.}
		view 732
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem &tmp [temp0 100])
		(switch theVerb
			(verbLook
				(if (not paidBouncer)
					(TimePrint 730 14)
				else
					(TimePrint 730 15)
					(TimePrint 730 16 #at -1 185)
				)
			)
			(verbZipper
				(TimePrint 730 17)
			)
			(verbTalk
				(switch roomState
					(0
						(Say The_Bouncer 730 18 #dispose)
					)
					(2
						(cond 
							((< (ego y?) y)
								(Say The_Bouncer 730 19 #dispose)
							)
							((Random 0 1)
								(Say The_Bouncer 730 20 #dispose)
							)
							(else
								(Say The_Bouncer 730 21 #dispose)
							)
						)
					)
					(1
						(if (< (ego y?) y)
							(Say The_Bouncer 730 22 #dispose)
						else
							(Say The_Bouncer 730 23 #dispose)
						)
					)
				)
			)
			(verbUse
				(switch theItem
					(iSilverDollar
						(cond 
							(
								(not
									(= paidBouncer
										(switch roomState
											(0 0)
											(2 25)
											(1
												(if (cast contains: lana)
													500
												else
													(PrintD
														{"What's it gonna be?"}
														#new
														#title {The Bouncer}
														#button {I'll just watch} 25
														#button {Lemme at 'em!} 500
													)
												)
											)
										)
									)
								)
							)
							(local4
								(if local1
									(Say The_Bouncer 730 24 #dispose)
								else
									(Say The_Bouncer 730 25 #dispose)
								)
							)
							((ego sitting?)
								(ego setScript: sStandUp)
							)
							((> paidBouncer silverDollars)
								(Format @temp0 730 26 silverDollars)
								(Say ego @temp0)
								(TimePrint 730 27)
								(Say The_Bouncer 730 28 #dispose)
							)
							(else
								(if (== paidBouncer 25)
									(Say ego 730 29)
									(Say The_Bouncer 730 30 #dispose)
									(= local1 1)
								else
									(SolvePuzzle 12 fPaidForMudWrestling)
									(Say ego 730 31)
									(if (cast contains: lana)
										(Say The_Bouncer 730 32 #dispose)
									else
										(Say The_Bouncer 730 33 #dispose)
									)
									(= local1 0)
								)
								(= silverDollars (- silverDollars paidBouncer))
								(= local4 1)
								(if (not (cast contains: lana))
									(self setCycle: EndLoop self approachX: 43 approachY: 153)
								)
							)
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
	
	(method (cue)
		(self setCel: 0 posn: (- x 16) y stopUpd:)
		((curRoom obstacles?) dispose:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						18 189
						18 181
						93 130
						116 130
						116 121
						68 121
						35 138
						35 164
						0 164
					yourself:
				)
		)
	)
)

(instance herEyes of Prop
	(properties
		x 150
		y 57
		view 738
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance herMouth of Prop
	(properties
		x 150
		y 57
		view 738
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance The_Bouncer of Talker
	(properties
		nsTop 15
		nsLeft 20
		view 1732
		loop 3
		viewInPrint TRUE
		name "The Bouncer"
	)
	
	(method (init)
		(= bust bouncerBust)
		(= eyes bouncerEyes)
		(= mouth bouncerMouth)
		(super init: &rest)
	)
)

(instance bouncerBust of Prop
	(properties
		view 1732
		loop 1
	)
)

(instance bouncerEyes of Prop
	(properties
		view 1732
		loop 2
		cycleSpeed 20
	)
)

(instance bouncerMouth of Prop
	(properties
		nsTop 53
		nsLeft 9
		view 1732
		cycleSpeed 8
	)
)

(instance Lana of Talker
	(properties
		nsTop 15
		nsLeft 15
		view 1731
		loop 3
		viewInPrint TRUE
	)
	
	(method (init)
		(= bust lanaBust)
		(= eyes lanaEyes)
		(= mouth lanaMouth)
		(super init: &rest)
	)
)

(instance lanaBust of Prop
	(properties
		view 1731
		loop 1
	)
)

(instance lanaEyes of Prop
	(properties
		nsTop 31
		nsLeft 29
		view 1731
		loop 2
		cycleSpeed 30
	)
)

(instance lanaMouth of Prop
	(properties
		nsTop 40
		nsLeft 23
		view 1731
	)
)

(instance stage of Feature
	(properties
		x 152
		y 85
		nsTop 67
		nsLeft 91
		nsBottom 103
		nsRight 214
		description {the stage}
		sightAngle 40
		lookStr {The stage is where all the action takes place.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 730 34)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance runway of Feature
	(properties
		x 260
		y 83
		nsTop 77
		nsLeft 202
		nsBottom 89
		nsRight 319
		description {the runway}
		sightAngle 40
		lookStr {You wonder what sort of babes must lie in wait down that runway.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 730 35)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance soundFX of Sound)
