;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include game.sh) (include "10.shm")
(use Main)
(use Sleep)
(use CastOpen)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm10 0
)

(local
	oldSpeed
	theNoun
	rockPts = [		;unused
		153 164
		153 176
		139 181
		73 181
		73 175
		33 175
		33 157
		135 157
		]
	timesReadStone
	stoneOpened
	local20	;unused
	cycleTimer =  4
	local22	;unused
	tookAFruit
)
(instance rm10 of Room
	(properties
		noun N_ROOM
		picture 10
		style HSHUTTER
	)
	
	(method (init)
		(HandsOn)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 0
						319 0
						319 189
						229 189
						271 167
						297 118
						213 104
						115 101
						115 127
						78 127
						78 151
						26 151
						26 186
						90 186
						90 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						161 125
						231 125
						256 140
						234 146
						161 146
						120 138
					yourself:
				)
				(rockPoly
					type: PBarredAccess
					init:
						155 163
						154 178
						72 178
						72 163
					yourself:
				)
		)
		(ChangeGait MOVE_WALK FALSE)
		(|= disabledActions ACTION_RUN)
		(|= disabledActions ACTION_SNEAK)
		(Load VIEW 10)
		(theMusic
			stop:
			number: 24
			loop: -1
			play:
		)
		(super init:)
		(magicStone
			init:
			approachVerbs: V_DO
		)
		(fruit
			init:
			approachVerbs: V_DO
		)
		(trunk
			init:
;;;			setOnMeCheck: ftrControl cRED
		)
		(sky
			init:
;;;			setOnMeCheck: ftrControl cGREEN
		)
		(snow
			init:
		)
		(tree
			init:
			approachVerbs: V_DO
;;;			setOnMeCheck: ftrControl cBLUE
		)
		(meadow
			init:
;;;			setOnMeCheck: ftrControl cCYAN
		)
		(self setScript: sEnterFromSouth)
	)
	
	(method (doit)
		(super doit:)
		(if (not (-- cycleTimer))
			(= cycleTimer 10)
			(if (and (> numColors 16) (not (Btst fCharSheetActive)))
				(Palette PALCycle 232 237 1)
				(Palette PALCycle 238 243 -1)
				(Palette PALCycle 244 250 1)
			)
		)
		(cond 
			(script)
			((>= (ego y?) 187)
				(curRoom setScript: sExitSouth)
			)
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn10)
		(super dispose:)
		(= disabledActions 0)
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(switch theVerb
			(V_DETECT
				(messager say: N_MEADOW V_DETECT)
			)
			(V_SLEEP
				(if (not (NeedSleep))
					(messager say: N_ROOM V_SLEEP)
				else
					(SolvePuzzle f10SleepInMeadow 5 MAGIC_USER)
					(ego setScript: goToSleep)
				)
			)
			(V_OPEN
				(if stoneOpened
					(messager say: N_ROOM V_OPEN)
				else
					(self setScript: moveStoneAway)
				)
			)
			(V_FLAME
				(messager say: N_MEADOW V_FLAME C_SPELLFAIL)
			)
			(V_CALM
				(messager say: N_MEADOW V_CALM C_SPELLFAIL)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (cue)
		(switch theNoun
			(N_TREE
				(if Night
					(messager say: N_TREE V_LOOK C_NIGHT)
				else
					(messager say: N_TREE V_LOOK C_DAY)
				)
			)
			(N_SKY
				(if Night
					(messager say: N_SKY V_LOOK C_NIGHT)
				else
					(messager say: N_SKY V_LOOK C_DAY)
				)
			)
			(N_MAGICSTONE
				(if (not (Btst fLearnedCalm))
					(messager say: N_MAGICSTONE V_LOOK C_SEESCROLL 1)
				else
					(messager say: N_MAGICSTONE V_LOOK C_SEESCROLL 2)
				)
			)
		)
		(super cue:)
	)
	
	(method (newRoom n)
		(theMusic fade:)
		(super newRoom: n)
	)
)

(instance sky of Feature
	(properties
		x 146
		y 24
		noun N_SKY
		nsLeft 23
		nsBottom 48
		nsRight 269
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_MEADOW V_FLAME C_SPELLFAIL)
			)
			(V_LOOK
				(= theNoun noun)
				(messager say: N_SKY V_LOOK NULL 0 curRoom)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance tree of Feature
	(properties
		x 194
		y 91
		noun N_TREE
		nsTop 33
		nsLeft 101
		nsBottom 150
		nsRight 287
		onMeCheck cBLUE
		approachX 262
		approachY 142
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(= theNoun noun)
				(messager say: N_TREE V_LOOK NULL 0 curRoom)
			)
			(V_DO
				(fruit doVerb: V_DO)
			)
			(V_FLAME
				(messager say: N_MEADOW V_FLAME C_SPELLFAIL)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance meadow of Feature
	(properties
		x 164
		y 126
		noun N_MEADOW
		nsTop 63
		nsLeft 13
		nsBottom 189
		nsRight 315
		sightAngle 40
		onMeCheck cCYAN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(switch (Random 1 3)
					(1
						(messager say: N_MEADOW NULL C_SEEMEADOW1)
					)
					(2
						(messager say: N_MEADOW NULL C_SEEMEADOW3)
					)
					(3
						(messager say: N_MEADOW NULL C_SEEMEADOW2)
					)
				)
			)
			(V_DO
				(if (>= (ego has: iFlowers) 10)
					(messager say: N_MEADOW V_DO C_NOMOREFLOWERS)
				else
					(curRoom setScript: sPickFlowers)
				)
			)
			(V_FLAME
				(messager say: N_MEADOW V_FLAME C_SPELLFAIL)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance trunk of Feature
	(properties
		x 195
		y 112
		noun N_TRUNK
		nsTop 81
		nsLeft 164
		nsBottom 143
		nsRight 227
		onMeCheck cRED
		approachX 196
		approachY 151
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_MEADOW V_FLAME C_SPELLFAIL)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance snow of Feature
	(properties
		x 159
		y 1
		noun N_SNOW
		nsBottom 189
		nsRight 319
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_MEADOW V_FLAME C_SPELLFAIL)
			)
			(V_LOOK
				(if
					(or
						(and (> mouseY 51) (> mouseX 234))
						(and (> mouseY 85) (< mouseX 107))
					)
					(messager say: N_SNOW V_LOOK C_SEESNOWLINE)
				else
					(messager say: N_SNOW V_LOOK C_SEEMOUNTAINS)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fruit of Prop
	(properties
		x 262
		y 91
		noun N_FRUIT
		approachX 262
		approachY 142
		view 10
		priority 11
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (init)
		(= nightPalette (+ rEranasPeace 100))
		(PalVary PALVARYTARGET (+ rEranasPeace 100))
		(AssertPalette rEranasPeace)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (> freeMeals 2)
					(if tookAFruit
						(messager say: N_FRUIT V_DO NULL 2)
					else
						(messager say: N_FRUIT V_DO NULL 1)
						(= tookAFruit TRUE)
					)
				else
					(curRoom setScript: sTakeAFruit)
				)
			)
			(V_FLAME
				(messager say: N_MEADOW V_FLAME C_SPELLFAIL)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance scroll of Prop
	(properties
		x 131
		y 168
		noun N_MAGICSTONE
		view 10
		loop 3
		priority 13
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sTakeScroll)
			)
			(V_FLAME
				(messager say: N_MEADOW V_FLAME C_SPELLFAIL)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance magicStone of Prop
	(properties
		x 83
		y 175
		noun N_MAGICSTONE
		approachX 149
		approachY 166
		approachDist 5
		view 10
		loop 2
		priority 12
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if stoneOpened
					(= theNoun noun)
					(messager say: N_MAGICSTONE V_LOOK C_STONEOPEN 0 curRoom)
				else
					(switch (++ timesReadStone)
						(1
							(messager say: N_MAGICSTONE V_LOOK C_STONECLOSED)
						)
						(2
							(messager say: N_MAGICSTONE V_LOOK C_READSTONE1)
						)
						(3
							(messager say: N_MAGICSTONE V_LOOK C_READSTONE1)
						)
						(4
							(messager say: N_MAGICSTONE V_LOOK C_READSTONE1)
						)
						(else
							(messager say: N_MAGICSTONE V_LOOK C_READSTONE2)
						)
					)
				)
			)
			(V_DO
				(cond 
					((and stoneOpened (not (Btst fLearnedCalm)))
						(scroll doVerb: theVerb)
					)
					(stoneOpened
						(messager say: N_MAGICSTONE V_DO C_STONEOPEN)
					)
					(else
						(messager say: N_MAGICSTONE V_DO C_STONECLOSED)
					)
				)
			)
			(V_FLAME
				(messager say: N_MEADOW V_FLAME C_SPELLFAIL)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance moveStoneAway of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(CastOpen self self 150 154)
			)
			(2
				(magicStone setCycle: CycleTo 1 1 self)
				(rockPoly dispose:)
			)
			(3
				(if (not (Btst fLearnedCalm))
					(scroll init:)
				)
				(Face ego scroll)
				(magicStone setCycle: EndLoop self)
			)
			(4
				(= stoneOpened TRUE)
				(rockPoly
					type: PBarredAccess
					init:
						;this is a duplicate of the unused rockPts array
						153 164
						153 176
						139 181
						73 181
						73 175
						33 175
						33 157
						135 157
					yourself:
				)
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance sExitSouth of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(ego setMotion: PolyPath (ego x?) 245 self)
			)
			(2
				(curRoom newRoom: 12)
			)
		)
	)
)

(instance goToSleep of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(ego setMotion: PolyPath 160 160 self)
			)
			(2
				(ego setHeading: 180 self)
			)
			(3
				(ego
					view: 10
					setLoop: 4
					setCel: 0
					posn: (- (ego x?) 3) (+ (ego y?) 2)
					setCycle: EndLoop self
				)
			)
			(4
				(= ticks 12)
			)
			(5
				(ego
					setLoop: 5
					setCel: 0
					posn: (- (ego x?) 4) (+ (ego y?) 1)
					setCycle: EndLoop self
				)
			)
			(6
				(PalVary PALVARYSTART (curRoom picture?) 2)
				(if nightPalette
					(PalVary PALVARYTARGET nightPalette)
				)
				(= seconds 5)
			)
			(7
				(messager say: N_ROOM NULL NULL 1 self)
			)
			(8
				(= seconds 2)
			)
			(9
				(PalVary PALVARYREVERSE 4)
				(= seconds 2)
			)
			(10
				(EgoSleeps 5 0)
				(= seconds 2)
			)
			(11
				(ego setCycle: BegLoop self)
			)
			(12
				(ego
					setLoop: 4
					setCel: 6
					posn: (+ (ego x?) 4) (- (ego y?) 1)
					setCycle: BegLoop self
				)
			)
			(13
				(HandsOn)
				(ego posn: (+ (ego x?) 3) (- (ego y?) 2))
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance sTakeScroll of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(Bset fLearnedCalm)
				(ego learn: CALM 10)
				(SolvePuzzle f10LearnCalm 4 MAGIC_USER)
				(ego view: 510 setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(scroll hide:)
				(= ticks 12)
			)
			(3
				(ego setCel: 5 setCycle: BegLoop self)
			)
			(4
				(messager say: N_ROOM NULL NULL 3 self)
			)
			(5
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance rockPoly of Polygon
	(properties
		type PBarredAccess
	)
)

(instance sPickFlowers of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(= oldSpeed (ego cycleSpeed?))
				(ego
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: PolyPath 193 163 self
				)
			)
			(2
				(= register (Random 0 1))
				(ego
					view: 510
					setLoop: register
					setCel: 0
					cycleSpeed: 6
					setCycle: EndLoop self
				)
			)
			(3
				(if (ego has: iFlowers)
					(messager say: N_MEADOW V_DO C_GETMOREFLOWERS 0 self)
				else
					(messager say: N_MEADOW V_DO C_GETFLOWERS 0 self)
				)
			)
			(4
				(ego get: 26 5 setCycle: BegLoop self)
			)
			(5
				(NormalEgo)
				(if register
					(ego cycleSpeed: oldSpeed setHeading: 270 self)
				else
					(ego cycleSpeed: oldSpeed setHeading: 90 self)
				)
			)
			(6
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sEnterFromSouth of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks 10)
			)
			(1
				(HandsOff)
				(= ticks 10)
			)
			(2
				(ego
					init:
					posn: 160 245
					setLoop: -1
					setMotion: MoveTo 160 183 self
				)
			)
			(3
				(= ticks 12)
			)
			(4
				(if (not (Btst fBeenIn10))
					(messager say: N_ROOM NULL C_FIRSTENTRY)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sTakeAFruit of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(ego setHeading: 315 self)
			)
			(2
				(= cycles 2)
			)
			(3
				(ego view: 10 setLoop: 6 setCel: 0 setCycle: 0)
				(= ticks 30)
			)
			(4
				(ego setCel: 1)
				(= ticks 30)
			)
			(5
				(fruit hide:)
				(ego setCel: 0)
				(= ticks 30)
			)
			(6
				(messager say: N_ROOM NULL NULL 2 self)
			)
			(7
				(= freeMeals 4)
				(Bclr fHungry)
				(Bclr fStarving)
				(NormalEgo facingNW)
				(HandsOn)
				(fruit approachVerbs: NULL)
				(tree approachVerbs: NULL)
				(SolvePuzzle f10EatMeadowFruit 2)
				(self dispose:)
			)
		)
	)
)
