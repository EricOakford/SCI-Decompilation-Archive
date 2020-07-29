;;; Sierra Script 1.0 - (do not remove this comment)
(script# 790)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm790 0
)

(local
	local0
)
(instance rm790 of LLRoom
	(properties
		lookStr {You are inside the only open shop along the entire boardwalk. You wonder why this place doesn't join in the celebration of Donald Tramp's birthday.}
		picture 790
		south 760
	)
	
	(method (init)
		(LoadMany VIEW 1792 792)
		(ego init: normalize: actions: sitActions)
		(switch prevRoomNum
			(south
				(HandsOff)
				(ego x: 160)
				(curRoom setScript: sFromSouth)
			)
			(else 
				(HandsOn)
				(ego posn: 160 160 edgeHit: 0)
			)
		)
		(super init:)
		(theMusic number: 790 loop: -1 flags: mNOPAUSE play:)
		(skates init:)
		(curtain init: stopUpd:)
		(poster1 init:)
		(poster2 init:)
		(poster3 init:)
		(poster4 init:)
		(poster5 init:)
		(poster6 init:)
		(poster7 init:)
		(box init:)
		(light init:)
		(desk init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						250 189
						250 137
						224 120
						91 120
						63 151
						63 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						179 144
						217 144
						238 157
						198 157
					yourself:
				)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			(
				(and
					(IsObjectOnControl ego cBLUE)
					(not (cast contains: Ivana_a))
				)
				(HandsOff)
				(Ivana_a init: approachVerbs: verbUse verbTalk verbDo setCycle: Walk)
				(curRoom setScript: sIvanaEnters)
			)
		)
	)
	
	(method (dispose)
		(if (ego has: iRollerskates) (Bset fRentedSkates))
		(theMusic fade:)
		(super dispose:)
	)
)

(instance sIvanaEnters of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Ivana_a setMotion: MoveTo 121 107 self)
			)
			(1
				(curtain setCycle: EndLoop curtain)
				(Ivana_a setMotion: MoveTo 121 111 self)
			)
			(2
				(Ivana_a setPri: 6 setMotion: MoveTo 150 111 self)
			)
			(3
				(Ivana_a setHeading: 180 self)
			)
			(4
				(Say Ivana_b 790 0 #dispose)
				(Ivana_a stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sFromSouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego y: 250 setMotion: MoveTo 160 180 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance sTalk of Script
	
	(method (changeState newState &tmp temp0 [str 80])
		(switch (= state newState)
			(0
				(= local0 1)
				(Say ego 790 1 #at -1 185)
				(Say Ivana_b 790 2 #dispose #caller self)
			)
			(1
				(Format @str 790 3 250)
				(Say Ivana_b @str #dispose #caller self)
			)
			(2
				(Say ego 790 4 #at -1 185)
				(Say Ivana_b 790 5 #dispose #caller self)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance sCamcorder of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Say ego 790 6 #at -1 185)
				(Say ego 790 7 #at -1 185)
				(SolvePuzzle 8 fCameraForSkates)
				(Say Ivana_b 790 8 #dispose #caller self)
			)
			(1
				(TimePrint 790 9)
				(ego put: iCamcorder 790 get: iRollerskates)
				(HandsOn)
				(theIconBar curIcon: (theIconBar at: 0))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(self dispose:)
			)
		)
	)
)

(instance sRollerblades of Script
	(properties)
	
	(method (changeState newState &tmp price [str 80])
		(switch (= state newState)
			(0
				(Say ego 790 10 #at -1 185)
				(Say Ivana_b 790 11 #dispose #caller self)
			)
			(1
				(SolvePuzzle 3 fAskedAboutSkateDeposit)
				(if (ego has: iCamcorder)
					(= price (Random 100 240))
					(Format @str 790 12 price)
					(Say Ivana_b @str #dispose)
					(ego put: iRollerskates 0 get: iSilverDollar)
					(= silverDollars (+ silverDollars (- 250 price)))
				else
					(Say Ivana_b 790 13 #dispose)
					(ego put: iRollerskates 0 get: 0)
				)
				(HandsOn)
				(theIconBar curIcon: (theIconBar at: 0))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(self dispose:)
			)
		)
	)
)

(instance Ivana_a of Actor
	(properties
		x 147
		y 107
		description {Ivana Tramp}
		sightAngle 40
		approachX 130
		approachY 120
		lookStr {Ivana Tramp (known locally as "Wheels, The Blademaster") has plummeted several social levels since her divorce from The Donald.}
		view 792
		priority 3
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem &tmp temp0 [str 80])
		(switch theVerb
			(verbDo
				(TimePrint 790 14)
			)
			(verbZipper
				(TimePrint 790 15)
			)
			(verbTalk
				(cond 
					((ego has: iRollerskates)
						(if (Btst fRentedSkates)
							(Say Ivana_b 790 16 #dispose)
						else
							(Say Ivana_b 790 17 #dispose)
						)
					)
					((Btst fRentedSkates)
						(Say Ivana_b 790 18 #dispose)
					)
					((not local0)
						(HandsOff)
						(curRoom setScript: sTalk)
					)
					(else (Say Ivana_b 790 19 #dispose))
				)
			)
			(verbUse
				(switch theItem
					(iSilverDollar
						(cond 
							((and (ego has: iRollerskates) (not (Btst fRentedSkates)))
								(Say Ivana_b 790 20 #dispose)
							)
							((Btst fRentedSkates)
								(Say Ivana_b 790 21 #dispose)
							)
							((not local0)
								(HandsOff)
								(curRoom setScript: sTalk)
							)
							((< silverDollars 250)
								(Format @str 790 22 silverDollars)
								(Say ego @str #at -1 185)
								(Say Ivana_b 790 23 #dispose)
							)
							(else
								(Format @str 790 24 250)
								(Say ego @str #at -1 185)
								(SolvePuzzle 4 fSilverForSkates)
								(Say Ivana_b 790 25 #dispose)
								(if (not (= silverDollars (- silverDollars 250)))
									(ego put: iSilverDollar 0)
								)
								(ego get: iRollerskates)
								(theIconBar curIcon: (theIconBar at: ICON_WALK))
								(theGame setCursor: ((theIconBar curIcon?) cursor?))
							)
						)
					)
					(iCamcorder
						(cond 
							((Btst fRentedSkates)
								(Say Ivana_b 790 26 #dispose)
							)
							((ego has: iRollerskates)
								(Say Ivana_b 790 27 #dispose)
							)
							(else
								(HandsOff)
								(curRoom setScript: sCamcorder)
							)
						)
					)
					(iRollerskates
						(if (Btst fRentedSkates)
							(HandsOff)
							(curRoom setScript: sRollerblades)
						else
							(Say Ivana_b 790 28 #dispose)
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
)

(instance skates of Feature
	(properties
		x 177
		y 69
		nsTop 49
		nsLeft 148
		nsBottom 90
		nsRight 206
		description {the skates}
		sightAngle 40
		lookStr {From the many pairs of in-line skates on this rack, you feel safe in predicting that this store rents skates.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 790 29)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance curtain of Prop
	(properties
		x 123
		y 94
		description {the curtain}
		sightAngle 40
		lookStr {A beaded curtain blocks your view of the skate shop's back room.}
		view 790
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 790 30)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(self stopUpd:)
	)
)

(instance poster1 of Feature
	(properties
		x 64
		y 42
		nsTop 31
		nsLeft 54
		nsBottom 54
		nsRight 75
		description {the poster}
		sightAngle 40
		lookStr {You presume this poster advertises an amusement park for insects.}
	)
)

(instance poster2 of Feature
	(properties
		x 76
		y 75
		nsTop 51
		nsLeft 62
		nsBottom 100
		nsRight 90
		description {the poster}
		sightAngle 40
		lookStr {So that's what Ivana looked like when she was a redhead!}
	)
)

(instance poster3 of Feature
	(properties
		x 61
		y 113
		nsTop 96
		nsLeft 50
		nsBottom 131
		nsRight 73
		description {the poster}
		sightAngle 40
		lookStr {To demonstrate how good these skates are, the lady in the poster is eating one.}
	)
)

(instance poster4 of Feature
	(properties
		x 230
		y 75
		nsTop 44
		nsLeft 221
		nsBottom 82
		nsRight 240
		description {the poster}
		sightAngle 40
		lookStr {This poster advertises roller skate odor eaters.}
	)
)

(instance poster5 of Feature
	(properties
		x 238
		y 44
		nsTop 66
		nsLeft 225
		nsBottom 103
		nsRight 252
		description {the poster}
		sightAngle 40
		lookStr {"Keep your in-line skates in line with the all new `Edlin,' from Ed's Skate Aligning Corp."}
	)
)

(instance poster6 of Feature
	(properties
		x 253
		y 37
		nsTop 25
		nsLeft 246
		nsBottom 50
		nsRight 261
		description {the poster}
		sightAngle 40
		lookStr {"Better skating, no waiting."}
	)
)

(instance poster7 of Feature
	(properties
		x 250
		y 106
		nsTop 92
		nsLeft 241
		nsBottom 121
		nsRight 259
		description {the poster}
		sightAngle 40
		lookStr {"Nuclear Blades" for those who want to go REALLY fast!}
	)
)

(instance light of Feature
	(properties
		x 165
		y 37
		nsTop 30
		nsLeft 147
		nsBottom 44
		nsRight 184
		description {the light}
		sightAngle 40
		lookStr {Ivana has thoughtfully replaced the standard 60-watt bulb with this new 250-watt halogen.}
	)
)

(instance desk of Feature
	(properties
		x 158
		y 105
		nsTop 91
		nsLeft 90
		nsBottom 120
		nsRight 227
		description {the counter}
		sightAngle 40
		lookStr {Across this counter pass the world's sharpest `blades!'}
	)
)

(instance box of Feature
	(properties
		x 205
		y 144
		nsTop 133
		nsLeft 188
		nsBottom 156
		nsRight 223
		description {the box}
		sightAngle 40
		lookStr {A wooden crate resides incongruously in the center of the floor.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 790 31)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Ivana_b of Talker
	(properties
		nsTop 15
		nsLeft 20
		view 1792
		loop 3
		viewInPrint 1
	)
	
	(method (init)
		(= bust ivanaBust)
		(= eyes ivanaEyes)
		(= mouth ivanaMouth)
		(super init: &rest)
	)
)

(instance ivanaBust of Prop
	(properties
		view 1792
		loop 1
	)
)

(instance ivanaEyes of Prop
	(properties
		nsTop 41
		nsLeft 24
		view 1792
		loop 2
		cycleSpeed 30
	)
)

(instance ivanaMouth of Prop
	(properties
		nsTop 41
		nsLeft 24
		view 1792
		cycleSpeed 8
	)
)

(instance sitActions of Actions
	
	(method (doVerb theVerb theItem)
		(return
			(switch theVerb
				(verbUse
					(switch theItem
						(iRollerskates
							(TimePrint 790 32)
						)
						(else
							(return FALSE)
						)
					)
				)
				(else
					(return FALSE)
				)
			)
		)
	)
)
