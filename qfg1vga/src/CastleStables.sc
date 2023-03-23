;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40)
(include game.sh) (include "40.shm")
(use Main)
(use Sleep)
(use Teller)
(use Time)
(use Procs)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use Extra)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm40 0
)

(local
	horseEatCount
	sandCycles
	stablemanAtWindow
	stablemanSpeaks
	goodMorning
	dustCued
	local6
	workBreak
	local8	;unused
	oldSortedFeatures
	saveCycleSpeed
	saveMoveSpeed
	local12	;unused
	talkCount
	wasRunning
	stableTellMainBranch = [
		STARTTELL
		-21		;C_WORK
		C_HORSE
		-13		;C_BARON
		C_STABLES
		C_GUARDS
		-17		;C_MONSTER
		ENDTELL
		]
	stableTell1 = [
		STARTTELL
		C_PAY
		ENDTELL
		]
	stableTell2 = [
		STARTTELL
		C_BARONET
		C_MAGIC
		C_VALLEY
		ENDTELL
		]
	stableTell3 = [
		STARTTELL
		C_ANTWERP
		ENDTELL
		]
	[stableTellTree 7]
	stableTellKeys = [
		STARTTELL
		-21		;C_WORK
		-13		;C_BARON
		-17		;C_MONSTER
		ENDTELL
		]
)
(procedure (AlreadyClean)
	(messager say: N_ROOM NULL NULL 1)
)

(instance rakeMusic of Sound
	(properties
		number 77
		priority 1
		loop -1
	)
)

(instance rm40 of Room
	(properties
		noun N_ROOM
		picture 40
		horizon 60
	)
	
	(method (init &tmp theY manureCel soundNum)
		(= [stableTellTree 0] @stableTellMainBranch)
		(= [stableTellTree 1] @stableTell1)
		(= [stableTellTree 2] @stableTell2)
		(= [stableTellTree 3] @stableTell3)
		(= [stableTellTree 4] ENDTELL)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 0
						319 189
						0 189
						0 166
						33 165
						56 178
						184 179
						236 162
						94 141
						145 116
						145 105
						88 140
						67 130
						59 112
						0 108
						0 0
					yourself:
				)
		)
		(self style: DISSOLVE)
		(= soundNum (if Night 32 else 25))
		(if
			(or
				(== (theMusic prevSignal?) -1)
				(!= (theMusic number?) soundNum)
			)
			(theMusic stop: number: soundNum loop: -1 priority: 0 play:)
		)
		(super init: &rest)
		(= talkCount 0)
		(= oldSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(= saveCycleSpeed (ego cycleSpeed?))
		(= saveMoveSpeed (ego moveSpeed?))
		(self
			setFeatures:
				walls
				towers
				waggon
				grain
				houses
				waterBarrel
				mounts
				sky
				fence
		)
		;UPGRADE
;;;		(walls
;;;			init:
;;;			setOnMeCheck: ftrControl cCYAN
;;;		)
;;;		(towers
;;;			init:
;;;			setOnMeCreck: ftrControl cRED
;;;		)
;;;		(waggon
;;;			init:
;;;			setOnMeCheck: ftrControl cMAGENTA
;;;		)
;;;		(grain
;;;			init:
;;;			setOnMeCheck: ftrControl cBROWN
;;;		)
;;;		(houses
;;;			init:
;;;			setOnMeCheck: ftrControl cLGREY
;;;		)
;;;		(waterBarrel
;;;			init:
;;;			setOnMeCheck: ftrControl cGREY
;;;		)
;;;		(mounts
;;;			init:
;;;			setOnMeCheck: ftrControl cLBLUE
;;;		)
;;;		(sky
;;;			init:
;;;			setOnMeCheck: ftrControl cLGREEN
;;;		)
;;;		(fence
;;;			init:
;;;			setOnMeCheck: ftrControl cLCYAN
;;;		)
		
		(stableTeller init: stableMan @stableTellMainBranch @stableTellTree @stableTellKeys)
		(stableMan
			setLoop: 0
			cel: 0
			actions: stableTeller
			approachVerbs: V_TALK
			posn: 234 82
			init:
			stopUpd:
		)
		(horse
			posn: (Random 53 70) (Random 98 103)
			setPri: 5
			init:
			stopUpd:
			setScript: horseEats
		)
		(horseHead
			posn: (+ (horse x?) 8) (- (horse y?) 23)
			setPri: 6
			init:
		)
		(horseTail
			posn: (- (horse x?) 13) (- (horse y?) 12)
			setPri: 6
			init:
		)
		(= manureCel (if (Btst fStableClean) 2 else 0))
		(manure1
			setPri: 4
			setCel: manureCel
			ignoreActors:
			init:
			stopUpd:
		)
		(manure2
			setPri: 4
			setCel: manureCel
			ignoreActors:
			init:
			stopUpd:
		)
		(manure3
			setPri: 4
			setCel: manureCel
			ignoreActors:
			init:
			stopUpd:
		)
		(= theY (ego y?))
		(switch prevRoomNum
			(999
				(= goodMorning TRUE)
				(ego posn: 148 132 init: setScript: sleepyIntro)
			)
			(39
				(NormalEgo)
				(ego
					cel: (ego cel?)
					loop: (ego loop?)
					posn: 15 theY
					init:
				)
			)
			(41
				(NormalEgo)
				(ego posn: 2 120 init: setMotion: PolyPath 25 130)
			)
			(else 
				(NormalEgo)
				(ego posn: 33 128 init:)
			)
		)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (and (== (ego edgeHit?) WEST) (not (ego script?)))
			(ego setScript: headWest)
		)
		(if
			(and
				(not Night)
				(!= prevRoomNum 999)
				(or
					(== (ego onControl: origin) cGREEN)
					(== (ego onControl: origin) cBLUE)
				)
				(not (== (stableMan script?) intro))
				(not (ego script?))
				(not goodMorning)
			)
			(= goodMorning TRUE)
			(ego setMotion: 0)
			(stableMan setScript: intro)
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(= useSortedFeatures oldSortedFeatures)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(cond 
					((== (ego onControl: origin) cBLUE)
						(messager say: N_ROOM V_LOOK C_INSIDE)
					)
					((Btst fStableClean)
						(messager say: N_ROOM V_LOOK C_CLEAN)
					)
					(else
						(messager say: N_ROOM V_LOOK C_DIRTY)
					)
				)
			)
			(V_SLEEP
				(cond 
					((and (< 750 Clock) (< Clock 2550))
						(messager say: N_ROOM V_SLEEP C_TOO_EARLY)
					)
					((not (== (ego onControl: origin) cBLUE))
						(messager say: N_ROOM V_SLEEP C_INSIDE)
					)
					((not (Btst fStableClean))
						(messager say: N_ROOM V_SLEEP C_CLEAN)
					)
					((ego script?)
						(messager say: N_ROOM V_SLEEP C_WAIT)
					)
					(else
						(ego setScript: sleeper)
					)
				)
			)
			(V_FLAME
				(messager say: N_ROOM V_FLAME)
			)
			(V_DAZZLE
				(messager say: N_ROOM V_DAZZLE)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(messager say: N_ROOM NULL NULL 9)
	)
)

(instance walls of Feature
	(properties
		noun N_WALLS
		onMeCheck cCYAN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_WALLS V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance towers of Feature
	(properties
		noun N_TOWERS
		onMeCheck cRED
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_TOWERS V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance waggon of Feature
	(properties
		noun N_WAGON
		onMeCheck cMAGENTA
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_WAGON V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance grain of Feature
	(properties
		noun N_GRAIN
		onMeCheck cBROWN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_GRAIN V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance houses of Feature
	(properties
		noun N_HOUSES
		onMeCheck cLGREY
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_HOUSES V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance waterBarrel of Feature
	(properties
		noun N_WATERBARREL
		onMeCheck cGREY
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_WATERBARREL V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance mounts of Feature
	(properties
		noun N_MOUNTAINS
		onMeCheck cLBLUE
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_MOUNTAINS V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance sky of Feature
	(properties
		noun N_SKY
		onMeCheck cLGREEN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_SKY V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance fence of Feature
	(properties
		noun N_FENCE
		onMeCheck cLCYAN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_FENCE V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance manure1 of View
	(properties
		x 48
		y 132
		noun N_MANURE
		view 140
		loop 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fStableClean)
					(messager say: N_MANURE V_LOOK C_CLEAN)
				else
					(messager say: N_MANURE V_LOOK C_DIRTY)
				)
			)
			(else  (curRoom doVerb: &rest))
		)
	)
)

(instance manure2 of View
	(properties
		x 63
		y 143
		view 140
		loop 6
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(curRoom doVerb: theVerb &rest)
		else
			(manure1 doVerb: theVerb &rest)
		)
	)
)

(instance manure3 of View
	(properties
		x 93
		y 143
		view 140
		loop 6
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(curRoom doVerb: theVerb &rest)
		else
			(manure1 doVerb: theVerb &rest)
		)
	)
)

(instance theHourGlass of View
	(properties
		x 5
		y 10
		view 40
		loop 1
	)
	
	(method (init)
		(glass setPri: 3 setCel: 0 init: stopUpd:)
		(sand setPri: 1 init: setCycle: Forward startUpd:)
		(super init:)
	)
	
	(method (dispose)
		(glass dispose:)
		(sand dispose:)
		(rm40 setScript: 0)
		(super dispose:)
	)
)

(instance glass of Prop
	(properties
		view 40
	)
	
	(method (init)
		(self
			x: (+ (theHourGlass x?) 9)
			y: (+ (theHourGlass y?) 10)
		)
		(super init:)
	)
)

(instance sand of Prop
	(properties
		view 40
		loop 2
	)
	
	(method (init)
		(self
			x: (+ (theHourGlass x?) 17)
			y: (+ (theHourGlass y?) 20)
		)
		(super init:)
	)
)

(instance horse of Prop
	(properties
		noun N_HORSE
		view 40
		loop 1
		cel 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_HORSE V_LOOK)
			)
			(V_DO
				(messager say: N_HORSE V_DO)
			)
			(V_TALK
				(messager say: N_HORSE V_TALK)
			)
			(V_FRUIT
				(messager say: N_HORSE V_FRUIT)
			)
			(V_VEGETABLES
				(messager say: N_HORSE V_FRUIT)
			)
			(V_FLOWERS
				(messager say: N_HORSE V_FRUIT)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance horseHead of Prop
	;was an Extra, but in preparation for the upgrade patch,
	; we're changing it to a Prop.
	(properties
		noun N_HORSE_HEAD
		view 40
		loop 6
		cycleSpeed 3
;		cycleType ExtraEndLoop
;		minPause 50
;		maxPause 110
;		minCycles 1
;		maxCycles 1
	)
	;This init: will allow it to function just as it was as an Extra.
	(method (init)
		((= cycler (Forward new:)) init: self)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_HORSE V_LOOK)
			)
			(V_DO
				(messager say: N_HORSE V_DO)
			)
			(V_TALK
				(messager say: N_HORSE V_TALK)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance horseTail of Prop ;Extra
	(properties
		noun N_HORSE
		view 40
		loop 5
		cycleSpeed 12
;		cycleType ExtraEndLoop
;		minPause 60
;		maxPause 100
;		minCycles 1
;		maxCycles 2
	)
	
	(method (init)
		((= cycler (Forward new:)) init: self)
		(super init:)
	)	
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_HORSE V_LOOK)
			)
			(V_DO
				(messager say: N_HORSE V_DO)
			)
			(V_TALK
				(messager say: N_HORSE V_TALK)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance aDustCloud of Actor
	(properties
		view 40
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb &rest)
	)
)

(instance stableMan of Actor
	(properties
		noun N_STABLEMAN
		approachX 209
		approachY 170
		view 42
	)
	
	(method (init)
		(= nightPalette 142)
		(PalVary PALVARYTARGET 142)
		(AssertPalette 42)
		(super init:)
	)
)

(instance stableTeller of Teller
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if stablemanAtWindow
					(messager say: N_STABLEMAN V_LOOK C_BORED)
				else
					(messager say: N_STABLEMAN V_LOOK C_CANT_HEAR)
				)
			)
			(V_TALK
				(if (> talkCount 5)
					(messager say: N_STABLEMAN V_ALTTALK C_BORED)
					(= talkCount 0)
				else
					(++ talkCount)
					(super doVerb: theVerb &rest)
				)
			)
			(V_FLAME (EgoDead 90 91 0 0 503))
			;The death icon was originally the "Hero in Jail" duplicated from view 503,
			; but the duplicate was removed from view 39 in the VGA remake,
			; showing a "tiny guard" instead.
			; Unfortunately, the correct icon is too large to fit with the message,
			; causing the game to crash with an "Invalid Rectangle" error, 
			; so the "Hero holding his belly in pain" icon (as used at the barracks) appears in its stead.
			(V_ROCK (EgoDead 90 91 0 0 503))
			(V_DAGGER (EgoDead 90 91 0 0 503))
			(V_SWORD (EgoDead 90 91 0 0 503))
			(else  (super doVerb: &rest))
		)
		(return TRUE)
	)
)

(instance horseEats of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= horseEatCount 0)
				(= seconds (Random 10 30))
			)
			(1
				(horseHead hide:)
				(horseTail hide:)
				(horse setLoop: 7 cel: 0 setCycle: EndLoop self)
			)
			(2
				(++ horseEatCount)
				(if (> (ego distanceTo: horse) 50)
					(horse loop: 8 setCycle: Forward)
					(= ticks (Random 20 36))
				else
					(self cue:)
				)
			)
			(3
				(horse setCycle: 0)
				(= ticks (Random 72 150))
			)
			(4
				(if (< horseEatCount (Random 3 6))
					(self changeState: 2)
				else
					(self cue:)
				)
			)
			(5
				(horse setLoop: 7 cel: 3 setCycle: BegLoop self)
			)
			(6
				(horse setLoop: 1 cel: 1 stopUpd:)
				(horseHead show:)
				(horseTail show:)
				(self changeState: 0)
			)
		)
	)
)

(instance sandsOfTime of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= sandCycles 0)
				(theHourGlass setPri: 2 init: stopUpd:)
				(= ticks 220)
			)
			(1
				(++ sandCycles)
				(glass setCel: (+ (glass cel?) 1))
				(= ticks 220)
			)
			(2
				(if (> sandCycles 6)
					(glass setCel: 8)
					(sand setCycle: 0)
					(= ticks 60)
				else
					(self changeState: 1)
				)
			)
			(3 (client setScript: 0))
		)
	)
)

(instance goToWork of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= saveCycleSpeed (ego cycleSpeed?))
				(= saveMoveSpeed (ego moveSpeed?))
				(if (== egoGait MOVE_RUN)
					(= wasRunning TRUE)
				)
				(ChangeGait MOVE_WALK 0)
				(if (== (ego onControl: origin) cBLUE)
					(self cue:)
				else
					(stableMan setCycle: BegLoop self)
				)
			)
			(1
				(if (== (ego onControl: origin) cBLUE)
					(self cue:)
				else
					(stableMan
						posn: 150 130
						setLoop: 1
						cel: 0
						setCycle: Walk
						setMotion: MoveTo 234 82 self
					)
				)
			)
			(2
				(stableMan stopUpd:)
				(if (== (ego onControl: origin) cBLUE)
					(self cue:)
				else
					(ego setMotion: PolyPath 114 128 self)
				)
			)
			(3 (self cue:))
			(4
				(SolvePuzzle f40WorkInStable 5)
				(client setScript: egoRakes)
			)
		)
	)
)

(instance egoRakes of Script
	(method (init)
		(super init: &rest)
		(rakeMusic init: play:)
	)
	
	(method (doit)
		(cond 
			(
				(and
					(== (ego cel?) 1)
					(or (== (self state?) 0) (== (self state?) 4))
				)
				(= dustCued TRUE)
			)
			((and dustCued (== (ego cel?) 2))
				(= dustCued FALSE)
				(aDustCloud
					posn:
						(if local6
							(- (ego x?) (Random 15 30))
						else
							(+ (ego x?) (Random 15 30))
						)
						(ego y?)
					setScript: aDustScript
				)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not workBreak)
					(rm40 setScript: sandsOfTime)
				)
				(theGame setCursor: waitCursor)
				(ego
					view: 140
					setLoop: 0
					cel: 0
					setStep: 1 1
					setPri: 8
					illegalBits: 0
					;tweaked to fix speed bug
					cycleSpeed: (if workBreak 4 else 2)
					moveSpeed: (if workBreak 6 else 2)
					setCycle: Forward
					setMotion: PolyPath 80 125 self
				)
				(aDustCloud
					setLoop: 9
					setPri: 9
					setStep: 6 4
					cel: 0
					cycleSpeed: 1
					ignoreActors:
					posn: 0 1000
					init:
				)
			)
			(1
				(ego setLoop: 2 cycleSpeed: 6 setCycle: Forward)
				(if workBreak
					(= ticks 180)
				else
					(= ticks 60)
				)
			)
			(2
				(if workBreak
					(messager say: N_ROOM NULL C_WORK_BREAK 1)
					(ego setLoop: 3 cycleSpeed: 6 setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(3 (self cue:))
			(4
				(if workBreak)
				(= local6 1)
				(ego
					setLoop: 1
					cel: 0
					;tweaked to fix speed bug
					cycleSpeed: (if workBreak 4 else 2)
					setCycle: Forward
					setMotion: PolyPath 114 128 self
				)
			)
			(5 (= ticks 18))
			(6
				(= local6 0)
				(if workBreak
					(= workBreak 0)
					(manure3 setCel: 2)
					(NormalEgo)
					(theGame setCursor: waitCursor)
					(rakeMusic fade:)
					(UseStamina 25)
					(client setScript: endRake)
				else
					(= workBreak TRUE)
					(manure1 setCel: 2)
					(manure2 setCel: 2)
					(self changeState: 0)
				)
			)
		)
	)
)

(instance endRake of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				;5 silvers for 3 hours of work!
				(AdvanceTime 3)
				(ego
					cycleSpeed: saveCycleSpeed
					moveSpeed: saveMoveSpeed
					setMotion: PolyPath 100 142 self
				)
			)
			(1
				(ego view: 140 setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(2
				(Bset fStableClean)
				(NormalEgo)
				(theGame setCursor: waitCursor)
				(ego x: (- (ego x?) 2) loop: 2)
				(SkillUsed STR 25)
				(SkillUsed VIT 40)
				(client setScript: getPaid)
			)
		)
	)
)

(instance getPaid of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(stableMan
					posn: 286 94
					setLoop: 0
					cel: 0
					show:
					setCycle: Walk
					setMotion: MoveTo 234 143 self
				)
				(= stablemanSpeaks TRUE)
			)
			(1
				(stableMan
					posn: 225 128
					setLoop: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(messager say: N_ROOM NULL NULL 2)
				(theHourGlass dispose:)
				(= ticks 56)
			)
			(3
				(ego setStep: 3 2 setMotion: PolyPath 205 161 self)
			)
			(4
				(NormalEgo)
				(theGame setCursor: waitCursor)
				(ego setMotion: PolyPath 209 156 self)
			)
			(5
				(stableMan setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(6
				(ego get: iSilver 5)
				(messager say: N_ROOM NULL NULL 3)
				(self cue:)
			)
			(7
				(stableMan setCycle: BegLoop)
				(ego setMotion: PolyPath 208 155 self)
			)
			(8
				(= stablemanAtWindow TRUE)
				(Face ego stableMan)
				(if wasRunning
					(ChangeGait MOVE_RUN FALSE)
				)
				(ego moveSpeed: saveMoveSpeed cycleSpeed: saveCycleSpeed)
				(HandsOn)
				(theGame setCursor: 940)
				(self dispose:)
			)
		)
	)
)

(instance sleeper of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(horse setScript: 0)
				(horseTail setCycle: 0)
				(horseHead setCycle: 0)
				(messager say: N_ROOM NULL NULL 4 self)
			)
			(1
				(ego hide:)
				(= cycles 5)
			)
			(2
				(messager say: N_ROOM NULL C_SNOOZING 1 self)
			)
			(3
				(cast eachElementDo: #hide)
				(curRoom drawPic: 400 DISSOLVE)
				(= seconds 2)
			)
			(4
				(EgoSleeps 5 0)
				(= seconds 1)
			)
			(5
				(if Night
					(curRoom drawPic: 40 DISSOLVE)
					(self cue:)
				else
					(self cue:)
				)
			)
			(6
				(= currentPalette 0)
				(curRoom drawPic: 40 DISSOLVE)
				(self cue:)
			)
			(7
				(if (not Night)
					(horse loop: 1 cel: 1 setCycle: 0 show:)
					(horseHead show:)
					(horseTail show:)
					(manure1 setCel: 0 show:)
					(manure2 setCel: 0 show:)
					(manure3 setCel: 0 show:)
					(ego show:)
					(client setScript: sleepyIntro)
				else
					(self cue:)
				)
			)
			(8
				(= prevRoomNum 999)
				(= curRoomNum 999)
				(= seconds 3)
			)
			(9
				(HandsOn)
				(NormalEgo)
				(curRoom newRoom: 40)
			)
		)
	)
)

(instance sleepyIntro of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(theGame setCursor: waitCursor)
				(= seconds 1)
			)
			(1 (messager say: N_ROOM NULL NULL 6 self))
			(2 (messager say: N_ROOM NULL C_AWAKEN_WORK 1 self))
			(3
				(horse setScript: horseEats)
				(client setScript: goToWork)
			)
		)
	)
)

(instance aDustScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(client
					setMotion:
						PolyPath
						(if local6
							(+ (ego x?) (Random 70 130))
						else
							(- (ego x?) (Random 70 130))
						)
						(- (+ (ego y?) 10) (Random 0 25))
					setCycle: EndLoop self
				)
			)
			(1
				(client posn: 0 1000 setMotion: 0 setCycle: 0)
				(self dispose:)
			)
		)
	)
)

(instance intro of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== (ego onControl: 1) 4)
					(ego setMotion: 0)
					(stableMan
						posn: 234 82
						setLoop: 0
						setCel: 0
						setCycle: Walk
						setMotion: MoveTo 155 130 self
					)
					(= stablemanSpeaks TRUE)
				else
					(self cue:)
				)
			)
			(1
				(if (== (ego onControl: origin) cGREEN)
					(stableMan
						setLoop: 2
						cel: 0
						posn: 146 115
						setCycle: EndLoop self
					)
				else
					(self cue:)
				)
			)
			(2
				(cond 
					((Btst fStableClean)
						(if (and (== timeODay TIME_SUNSET) (== (ego onControl: origin) cBLUE))
							(client setScript: 0)
						else
							(AlreadyClean)
							(HandsOff)
							(self changeState: 5)
						)
					)
					((Btst fMetStableman)
						(messager say: N_ROOM NULL NULL 7)
						(HandsOff)
						(theGame setCursor: ARROW_CURSOR TRUE)
						(= ticks 60)
					)
					(else
						(Bset fMetStableman)
						(HandsOff)
						(if (== (ego onControl: origin) cBLUE)
							(messager say: N_ROOM NULL NULL 8 curRoom)
							(= seconds 3)
						else
							(messager say: N_ROOM NULL NULL 9 self)
						)
					)
				)
			)
			(3
				(= seconds 1)
			)
			(4
				(theGame setCursor: ARROW_CURSOR TRUE)
				(switch
					(Print
						addButton: 0 N_ROOM NULL C_SAY_NO 1 0 25 40
						addButton: 1 N_ROOM NULL C_SAY_YES 1 0 5 40
						init:
					)
					(0
						(messager say: N_ROOM NULL NULL 12 self)
					)
					(1
						(if (== (ego onControl: origin) cBLUE)
							(messager say: N_ROOM NULL NULL 10)
						else
							(messager say: N_ROOM NULL NULL 11)
						)
						(theGame setCursor: waitCursor TRUE)
						(ego setScript: goToWork)
					)
				)
			)
			(5
				(theGame setCursor: waitCursor TRUE)
				(HandsOn)
				(if (== (ego onControl: origin) cBLUE)
					(self cue:)
				else
					(stableMan setCycle: BegLoop self)
				)
			)
			(6
				(if (== (ego onControl: origin) cBLUE)
					(self cue:)
				else
					(stableMan
						posn: 150 130
						setLoop: 1
						cel: 0
						setCycle: Walk
						setMotion: MoveTo 234 82 self
					)
				)
			)
			(7
				(stableMan stopUpd:)
				(if (not (ego script?))
					(HandsOn)
				)
				(= stablemanSpeaks FALSE)
				(client setScript: 0)
			)
		)
	)
)

(instance headWest of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (curRoom obstacles?)
					((curRoom obstacles?) dispose:)
					(curRoom obstacles: 0)
				)
				(= ticks 30)
			)
			(1
				(if (> (ego y?) 125)
					(ego setMotion: PolyPath -15 (ego y?) self)
				else
					(ego setMotion: PolyPath -15 (- (ego y?) 15) self)
				)
			)
			(2
				(HandsOn)
				(if (> (ego y?) 125)
					(curRoom newRoom: 39)
				else
					(curRoom newRoom: 41)
				)
			)
		)
	)
)
