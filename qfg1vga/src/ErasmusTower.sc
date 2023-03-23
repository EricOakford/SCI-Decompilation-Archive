;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include game.sh) (include "31.shm")
(use Main)
(use Teller)
(use Procs)
(use Print)
(use Talker)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm31 0
	erasmus 1
	fenrus 2
)
(enum 1	;reasons for leaving
	wontAnswerRiddles
	knowAllPunchlines
	thoughtsGiveYouAway
	notInMyHouse
	reallyMustGo
	cantSleepHere
)

(local
	teleportCued
	reasonForLeaving
	local2
	erasmusDrank
	erasmusDrinking
	teaTimer
	jokeTimer
	numJoke
	yesCount
	local9
	askedErasmus
	local11
	local12
	local13
	local14
	postMazeGame
	oldSortedFeatures
	local17
	local18
	jokeCase
	[local20 10]
	erasmusTellMainBranch = [
		STARTTELL
		C_ERASMUS
		C_FENRUS
		-16		;C_MAGIC
		-5		;C_BRIGANDS
		-17		;C_MAGEMAZE
		ENDTELL
		]
	erasmusTell1 = [
		STARTTELL
		C_ZARA
		-4		;C_BABAYAGA
		-10		;C_ERANA
		-6		;C_SPELLCASTERS
		ENDTELL
		]
	erasmusTell2 = [
		STARTTELL
		C_NINCOMPOOP
		C_WARLOCK
		C_NECROMANCER
		ENDTELL
		]
	erasmusTell3 = [
		STARTTELL
		C_OPEN
		C_FETCH
		-26		;C_TRIGGER
		C_DAZZLE
		ENDTELL
		]
	erasmusTell4 = [
		STARTTELL
		-23		;C_PROTECTSPELL
		ENDTELL
		]
	erasmusTell5 = [
		STARTTELL
		-8		;C_CURSES
		ENDTELL
		]
	erasmusTell6 = [
		STARTTELL
		C_INITIATION
		C_SPELLS
		ENDTELL
		]
	erasmusTell7 = [
		STARTTELL
		C_HERMIT
		C_TRAP
		ENDTELL
		]
	erasmusTell8 = [
		STARTTELL
		C_PLACES
		ENDTELL
		]
	erasmusTell9 = [
		STARTTELL
		-7		;C_COUNTERCURSE
		ENDTELL
		]
	erasmusTell10 = [
		STARTTELL
		C_MAGICMIRROR
		ENDTELL
		]
	[erasmusTellTree 15]
	erasmusTellKeys = [
		STARTTELL
		-16		;C_MAGIC
		-5		;C_BRIGANDS
		-17		;C_MAGEMAZE
		-10		;C_ERANA
		-4		;C_BABAYAGA
		-6		;C_SPELLCASTERS
		-26		;C_TRIGGER
		-23		;C_PROTECTSPELL
		-8		;C_CURSES
		-7		;C_COUNTERCURSE
		ENDTELL
		]
	fenrusTellMainBranch = [
		STARTTELL
		C_FENRUS
		C_ERASMUS
		ENDTELL
		]
	[fenrusTellTree 6]
	fenrusTellKeys = [STARTTELL ENDTELL]
)
(instance rm31 of Room
	(properties
		picture 31
		style WIPEUP
	)
	
	(method (init)
		(= [erasmusTellTree 0] @erasmusTellMainBranch)
		(= [erasmusTellTree 1] @erasmusTell1)
		(= [erasmusTellTree 2] @erasmusTell2)
		(= [erasmusTellTree 3] @erasmusTell3)
		(= [erasmusTellTree 4] @erasmusTell4)
		(= [erasmusTellTree 5] @erasmusTell5)
		(= [erasmusTellTree 6] @erasmusTell6)
		(= [erasmusTellTree 7] @erasmusTell7)
		(= [erasmusTellTree 8] @erasmusTell8)
		(= [erasmusTellTree 9] @erasmusTell9)
		(= [erasmusTellTree 10] @erasmusTell10)
		(= [erasmusTellTree 11] ENDTELL)
		(= [fenrusTellTree 0] @fenrusTellMainBranch)
		(= [fenrusTellTree 1] ENDTELL)
		(walkHandler add: self)
		(LoadMany RES_VIEW 31 199 530 1034 1031)
		(LoadMany RES_SOUND 28 36)
		(Load RES_MESSAGE 31)
		(= teaTimer (Random 50 100))
		(= jokeTimer 100)
		(= numJoke (Random 0 7))
		(= local13 1)
		(= local14 1)
		(StatusLine enable:)
		(super init:)
		(= oldSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(NormalEgo)
		(door init:)
		(egoHead init: posn: 500 500 hide:)
		(self
			setFeatures:
				pillars
				curtain
				table
				stairs
				wWindow
				fenrusPad
				gStone
				pStone
				walls
		)
		;UPGRADE
;;;		(pillars
;;;			init:
;;;			setOnMeCheck: ftrControl cBLUE
;;;		)
;;;		(curtain
;;;			init:
;;;			setOnMeCheck: ftrControl cGREEN
;;;		)
;;;		(table
;;;			init:
;;;			setOnMeCheck: ftrControl cCYAN
;;;		)
;;;		(stairs
;;;			init:
;;;			setOnMeCheck: ftrControl cRED
;;;		)
;;;		(wWindow
;;;			init:
;;;			setOnMeCheck: ftrControl cMAGENTA
;;;		)
;;;		(fenrusPad
;;;			init:
;;;			setOnMeCheck: ftrControl cBROWN
;;;		)
;;;		(gStone
;;;			init:
;;;			setOnMeCheck: ftrControl cLGREY
;;;		)
;;;		(pStone
;;;			init:
;;;			setOnMeCheck: ftrControl cGREY
;;;		)
;;;		(walls
;;;			init:
;;;			setOnMeCheck: ftrControl cLBLUE
;;;		)
		
		(wizLegs addToPic:)
		(wizChair addToPic:)
		(egoChair addToPic:)
		(cup init:)
		(erasmusTeller init: wizard @erasmusTellMainBranch @erasmusTellTree @erasmusTellKeys)
		(wizard init: setPri: 15 actions: erasmusTeller)
		(if (== prevRoomNum 32)
			(fenrusTeller
				init: fenrusBody @fenrusTellMainBranch @fenrusTellTree @fenrusTellKeys
			)
			(fenrusBody
				init:
				setCel: 8
				setPri: 15
				actions: fenrusTeller
			)
			(self setScript: gameOver)
		else
			(ego init: actions: egoActions)
			(cheese init:)
			(theMusic number: 117 loop: -1 init: play:)
			(self setScript: intoTheTower)
		)
	)
	
	(method (doit)
		;added to address timer bug
		(if (< (Abs (- gameTime name)) 2) (return))
		(= name gameTime)
		(if (not (Btst fCharSheetActive))
			(if (== postMazeGame TRUE)
				(Palette PALCycle 232 246 -1)
				(Palette PALCycle 247 254 -1)
			)
			(if teleportCued
				(= teleportCued FALSE)
				(= local14 0)
				(= local13 0)
				(self setScript: teleportOut)
			)
			(cond 
				((curRoom script?))
				((and local13 (> jokeTimer 1))
					(-- jokeTimer)
				)
				((== jokeTimer 1)
					(if modelessDialog
						(modelessDialog dispose:)
					)
					(HandsOff)
					(= local13 0)
					(= local14 0)
					(if (== numJoke 7)
						(= numJoke 0)
					else
						(++ numJoke)
					)
					(switch numJoke
						(0
							(= local18 4)
							(= jokeCase C_JOKE_BROOM)
						)
						(1
							(= local18 3)
							(= jokeCase C_JOKE_CHEETAUR)
						)
						(2
							(= local18 3)
							(= jokeCase C_JOKE_COW)
						)
						(3
							(= local18 3)
							(= jokeCase C_JOKE_OTTO)
						)
						(4
							(= local18 4)
							(= jokeCase C_JOKE_PURPLE)
						)
						(5
							(= local18 2)
							(= jokeCase C_JOKE_TORCH)
						)
						(6
							(= local18 5)
							(= jokeCase C_JOKE_TRICERATOPS)
						)
						(7
							(= local18 3)
							(= jokeCase C_JOKE_TYRANNOSAURUS)
						)
					)
					(self setScript: tellJoke)
				)
			)
			(cond 
				((curRoom script?))
				((and local14 (> teaTimer 1))
					(-- teaTimer)
				)
				((== teaTimer 1)
					(= teaTimer (Random 450 750))
					(= local13 0)
					(wizard setScript: drinkTea)
				)
			)
			(super doit:)
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn31)
		(walkHandler delete: self)
		(= useSortedFeatures oldSortedFeatures)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(= reasonForLeaving reallyMustGo)
				(curRoom setScript: teleportOut)
			)
			(V_SLEEP
				(= reasonForLeaving cantSleepHere)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_OPEN
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_DETECT
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_ZAP
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_TRIGGER
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_DAZZLE
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_CALM
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_FETCH
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance wizard of Actor
	(properties
		x 67
		y 108
		noun N_WIZARD
		view 31
		loop 1
		cycleSpeed 1
	)
)

(instance erasmusTeller of Teller
	(method (showDialog)
		(super showDialog:
			-17 (Btst fWizKnowsEgoHasMagic)	;C_MAGEMAZE
		)
	)
	
	(method (doChild)
		(return
			(if (== query -17)	;C_MAGEMAZE
				(super doChild: query)
				(return query)
			else
				(super doChild: query)
				(return query)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(egoHead setCel: 0)
		(switch theVerb
			(V_TALK
				(= local14 0)
				(= local13 0)
				(+= jokeTimer 5)
				(SolvePuzzle f31TalkToErasmus 1)
				(if erasmusDrinking
					(if askedErasmus
						(messager say: N_ROOM NULL C_WIZ_DRINKING 1)
					else
						(messager say: N_ROOM NULL C_WIZ_DRINKING 2)
					)
				else
					(super doVerb: theVerb &rest)
					(= local14 1)
					(= local13 1)
				)
			)
			(V_DO
				(= reasonForLeaving thoughtsGiveYouAway)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_FLAME
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance wizLegs of View
	(properties
		x 69
		y 128
		view 31
		loop 2
		priority 8
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(wizard doVerb: theVerb &rest)
	)
)

(instance wizChair of View
	(properties
		x 60
		y 120
		view 31
		cel 3
		priority 8
		signal (| ignrAct fixPriOn)
	)
)

(instance egoChair of View
	(properties
		x 161
		y 116
		view 31
		cel 2
		priority 5
		signal (| ignrAct fixPriOn)
	)
)

(instance cheese of Actor
	(properties
		x 128
		y 116
		noun N_CHEESE
		view 31
	)
)

(instance egoHead of Actor
	(properties
		x 161
		y 87
		view 31
		loop 4
	)
)

(instance cup of View
	(properties
		x 78
		y 103
		noun N_CUP
		view 31
		cel 1
		priority 8
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb)
		(if erasmusDrank
			(messager say: N_CUP V_LOOK NULL 1)
		else
			(messager say: N_CUP V_LOOK NULL 2)
		)
	)
)

(instance erasmus of Talker
	(properties
		x 10
		y 10
		view 1034
		talkWidth 180
		textX 21
		textY 110
	)
	
	(method (init)
		(= nightPalette 2034)
		(PalVary PALVARYTARGET 2034)
		(AssertPalette 1034)
		(= font userFont)
		(super init: wizBust wizEyes wizMouth &rest)
	)
	
	(method (doit)
		;added to address timer bug
		(if (< (Abs (- gameTime name)) 2) (return))
		(= name gameTime)
		(egoHead setCel: 0)
		(super doit:)
	)
)

(instance wizBust of Prop
	(properties
		view 1034
	)
)

(instance wizMouth of Prop
	(properties
		nsTop 53
		nsLeft 54
		view 1034
		loop 1
	)
)

(instance wizEyes of Prop
	(properties
		nsTop 41
		nsLeft 52
		view 1034
		loop 2
	)
)

(instance fenrus of Talker
	(properties
		x 190
		y 10
		view 1031
		talkWidth 210
		textX -120
		textY 110
	)
	
	(method (init)
		(= nightPalette 2031)
		(PalVary PALVARYTARGET 2031)
		(AssertPalette 1031)
		(= font userFont)
		(super init: fenrusBust fenrusEyes fenrusMouth &rest)
	)
	
	(method (doit)
		;added to address timer bug
		(if (< (Abs (- gameTime name)) 2) (return))
		(= name gameTime)
		(egoHead setCel: 1)
		(super doit:)
	)
)

(instance fenrusBust of Prop
	(properties
		view 1031
	)
)

(instance fenrusMouth of Prop
	(properties
		nsTop 63
		nsLeft 7
		view 1031
		loop 1
	)
)

(instance fenrusEyes of Prop
	(properties
		nsTop 44
		nsLeft 11
		view 1031
		loop 2
	)
)

(instance fenrusBody of Prop
	(properties
		x 115
		y 182
		noun N_FENRUS_BODY
		view 199
	)
)

(instance fenrusTeller of Teller
	(method (doVerb theVerb theItem)
		(egoHead setCel: 1)
		(switch theVerb
			(V_DO
				(= reasonForLeaving thoughtsGiveYouAway)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_OPEN
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_DETECT
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_TRIGGER
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_DAZZLE
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_CALM
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_FLAME
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(V_FETCH
				(= reasonForLeaving notInMyHouse)
				(= local13 0)
				(curRoom setScript: teleportOut)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Magic of Prop
	(properties
		view 530
		cycleSpeed 2
	)
)

(instance door of Prop
	(properties
		x 131
		view 31
		loop 6
		signal ignrAct
		cycleSpeed 8
	)
	
	(method (init)
		(if (== prevRoomNum 32)
			(self cel: 5)
			(= postMazeGame TRUE)
		else
			(self cel: 0)
		)
		(super init:)
	)
)

(instance pillars of Feature
	(properties
		noun N_PILLARS
		nsBottom 189
		nsRight 319
		onMeCheck cBLUE
	)
)

(instance curtain of Feature
	(properties
		noun N_CURTAIN
		nsBottom 189
		nsRight 319
		onMeCheck cGREEN
	)
)

(instance table of Feature
	(properties
		noun N_TABLE
		nsBottom 189
		nsRight 319
		onMeCheck cCYAN
	)
)

(instance stairs of Feature
	(properties
		noun N_STAIRS
		nsBottom 189
		nsRight 319
		onMeCheck cRED
	)
)

(instance wWindow of Feature
	(properties
		noun N_WINDOW
		nsBottom 189
		nsRight 319
		onMeCheck cMAGENTA
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(if Night
				(messager say: N_WINDOW V_LOOK C_NIGHT)
			else
				(messager say: N_WINDOW V_LOOK)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance fenrusPad of Feature
	(properties
		noun N_FENRUS_PAD
		nsBottom 189
		nsRight 319
		onMeCheck cBROWN
	)
)

(instance gStone of Feature
	(properties
		noun N_GSTONE
		nsBottom 189
		nsRight 319
		onMeCheck cLGREY
	)
)

(instance pStone of Feature
	(properties
		noun N_PSTONE
		nsBottom 189
		nsRight 319
		onMeCheck cGREY
	)
)

(instance walls of Feature
	(properties
		noun N_WALLS
		nsBottom 189
		nsRight 319
		onMeCheck cLBLUE
	)
)

(instance tellJoke of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local14 0)
				(messager say: N_ROOM NULL jokeCase 1 self)
			)
			(1
				1
				(switch
					(Print
						addButton: 0 N_ROOM NULL NULL 2 0 19 31
						addButton: 1 N_ROOM NULL NULL 1 0 0 31
						init:
					)
					(0 (self changeState: 3))
					(1 (self changeState: 2))
				)
			)
			(2
				(if (== local18 2)
					(messager say: N_ROOM NULL jokeCase 2)
				else
					(messager say: N_ROOM NULL jokeCase 3)
				)
				(if (!= jokeCase 2)
					(++ yesCount)
				)
				(if (== yesCount 7)
					(= reasonForLeaving knowAllPunchlines)
					(= teleportCued TRUE)
				)
				(self changeState: 6)
			)
			(3
				3
				(if (== local18 2)
					(messager say: N_ROOM NULL jokeCase 2)
					(self changeState: 6)
				else
					(messager say: N_ROOM NULL jokeCase 2 self)
				)
			)
			(4
				4
				(if (== local18 3)
					(self changeState: 6)
				else
					(messager say: N_ROOM NULL jokeCase 4 self)
				)
			)
			(5
				5
				(if (== local18 4)
					(self cue:)
				else
					(messager say: N_ROOM NULL jokeCase 5 self)
				)
			)
			(6
				6
				(= local14 1)
				(= local13 1)
				(HandsOn)
				(= jokeTimer (Random 300 400))
				(self dispose:)
			)
		)
	)
)

(instance intoTheTower of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setPri: 8
					setLoop: 7
					posn: 250 208
					setMotion: MoveTo 191 167 self
				)
			)
			(1
				(NormalEgo)
				(ego setMotion: MoveTo 177 145 self)
			)
			(2
				(messager say: N_ROOM NULL C_FIRST_MEETING 1 self)
			)
			(3
				(ego setMotion: MoveTo 179 129 self)
			)
			(4
				(ego setPri: 6 setMotion: MoveTo 162 118 self)
			)
			(5
				(ego
					view: 31
					setLoop: 5
					setCel: 0
					posn: 162 118
					setCycle: CycleTo 4 1 self
				)
				(egoInChair init:)
			)
			(6
				(messager say: N_ROOM NULL C_FIRST_MEETING 2 self)
				(ego setCel: 5 signal: (| (ego signal?) stopUpdOn))
				(egoHead posn: 161 88 show:)
			)
			(7
				(fenrusTeller
					init: fenrusBody @fenrusTellMainBranch @fenrusTellTree @fenrusTellKeys
				)
				(fenrusBody
					init:
					setPri: 15
					setCycle: CycleTo 4 1 self
					actions: fenrusTeller
				)
			)
			(8
				(fenrusSound play:)
				(fenrusBody setCycle: EndLoop self)
			)
			(9
				(fenrusBody setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(10
				(messager say: N_ROOM NULL C_FIRST_MEETING 3 self)
			)
			(11
				(cheese
					setLoop: 0
					setMotion: MoveTo (fenrusBody x?) (- (fenrusBody y?) 5) self
				)
			)
			(12
				(cheese dispose:)
				(fenrusBody setLoop: 5 setCel: 0 setCycle: EndLoop self)
			)
			(13
				(fenrusBody setCycle: BegLoop self)
			)
			(14
				(= local17 (Random 10 15))
				(fenrusBody setLoop: 1 setCel: 0 setCycle: EndLoop)
				(= seconds 3)
			)
			(15
				(messager say: N_ROOM NULL C_FIRST_MEETING 4 self)
			)
			(16
				(if (>= [egoStats MAGIC] 10)
					(self cue:)
				else
					(HandsOn)
					(= local13 1)
					(= local14 1)
					(client setScript: 0)
				)
			)
			(17
				(= local11 0)
				(if (Btst fErasmusAskedMaze)
					(client setScript: intoMaze)
				else
					(client setScript: askSpell)
				)
			)
		)
	)
)

(instance intoMaze of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst fErasmusAskedMaze)
					(messager say: N_WIZARD NULL C_PRE_MAZE 12 self)
				else
					(messager say: N_WIZARD NULL C_PRE_MAZE 11 self)
				)
			)
			(1
				(switch
					(Print
						addButton: 0 N_ROOM NULL NULL 2 0 0 31
						addButton: 1 N_ROOM NULL NULL 1 0 19 31
						init:
					)
					(0 (self changeState: 2))
					(1 (self changeState: 4))
				)
			)
			(2
				(messager say: N_WIZARD NULL C_PRE_MAZE 13 self)
			)
			(3
				(HandsOn)
				(= local13 1)
				(= local14 1)
				(self dispose:)
			)
			(4
				(if
					(and
						;? These can't be right
						[egoStats 142]
						[egoStats 144]
						[egoStats 148]
						[egoStats 149]
					)
					(Bset fErasmusAskedMaze)
				)
				(client setScript: goGame)
				(self dispose:)
			)
		)
	)
)

(instance askSpell of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fWizKnowsEgoHasMagic)
				(messager say: N_WIZARD NULL C_PRE_MAZE (+ 1 (* 2 local11)) self)
			)
			(1
				(switch
					(Print
						addButton: 0 N_ROOM NULL NULL 2 0 0 31
						addButton: 1 N_ROOM NULL NULL 1 0 19 31
						init:
					)
					(0 (self changeState: 2))
					(1 (self changeState: 4))
				)
			)
			(2
				(messager say: N_WIZARD NULL C_PRE_MAZE (+ 2 (* 2 local11)) self)
			)
			(3
				(HandsOn)
				(= local13 1)
				(= local14 1)
				(self dispose:)
			)
			(4
				(if (< local11 3)
					(++ local11)
					(self changeState: 0)
				else
					(self cue:)
				)
			)
			(5
				(messager say: N_WIZARD NULL C_PRE_MAZE 9 self)
			)
			(6
				(switch
					(Print
						addButton: 0 N_ROOM NULL NULL 2 0 0 31
						addButton: 1 N_ROOM NULL NULL 1 0 19 31
						init:
					)
					(0
						(= register 0)
						(self changeState: 7)
					)
					(1
						(= register 1)
						(self changeState: 7)
					)
				)
			)
			(7
				(if [egoStats DAZZLE]
					(self cue:)
				else
					(messager say: N_WIZARD NULL C_PRE_MAZE 10 self)
				)
			)
			(8
				(if register
					(client setScript: goGame)
				else
					(client setScript: intoMaze)
				)
			)
		)
	)
)

(instance gameOver of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 60)
			)
			(1
				(doorSound play:)
				(ego
					view: 31
					setLoop: 5
					setCel: 5
					setCycle: 0
					posn: 162 118
					setPri: 6
					actions: egoActions
					init:
				)
				(egoHead posn: 161 88 show:)
				(door setCycle: BegLoop self)
			)
			(2
				(doorSound stop:)
				(door stopUpd:)
				(= postMazeGame NULL)
				(messager say: N_ROOM NULL C_POST_MAZE 1 self)
			)
			(3
				(ego signal: (| (ego signal?) stopUpdOn))
				(= local13 1)
				(= local14 1)
				(theIconBar enable:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance drinkTea of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= erasmusDrinking TRUE)
				(= local14 0)
				(wizard cel: 1)
				(cup hide:)
				(= ticks 4)
			)
			(1
				(wizard cycleSpeed: 12 setCycle: EndLoop self)
			)
			(2
				(= ticks 120)
			)
			(3
				(wizard setCycle: BegLoop self)
			)
			(4
				(cup show:)
				(= erasmusDrinking NULL)
				(= local14 1)
				(= local13 1)
				(= erasmusDrank TRUE)
				(wizard setCel: 0 setScript: 0 stopUpd:)
				(= ticks 1)
			)
		)
	)
)

(instance goGame of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self cue:)
				(doorSound play:)
			)
			(1
				(= postMazeGame TRUE)
				(door setCycle: EndLoop self)
			)
			(2 (= seconds 5))
			(3
				(doorSound stop:)
				(Bset fWizKnowsEgoHasMagic)
				(theMusic fade:)
				(curRoom newRoom: 32)
			)
		)
	)
)

(instance teleportOut of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= jokeTimer 0)
				(= teaTimer 0)
				(wizard setScript: 0)
				(if (> reasonForLeaving notInMyHouse)
					(= reasonForLeaving notInMyHouse)
				)
				(= ticks 30)
			)
			(1
				(messager say: N_WIZARD NULL C_WARP_OUT reasonForLeaving self)
			)
			(2
				(wizard setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(3
				(Magic
					ignoreActors:
					setPri: (+ (ego priority?) 1)
					posn: (- (ego x?) 2) (ego y?)
					init:
					setCycle: CycleTo 5 1 self
				)
			)
			(4
				(ego hide:)
				(egoHead hide:)
				(egoInChair dispose:)
				(fenrusSound play:)
				(Magic setCycle: CycleTo 3 -1 self)
			)
			(5 (Magic setCycle: EndLoop self))
			(6
				(Bset fErasmusWarpOut)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 (| BLACKOUT IRISOUT))
				(= ticks 12)
			)
			(7 (curRoom newRoom: 28))
		)
	)
)

(instance fenrusSound of Sound
	(properties
		number 28
	)
)

(instance doorSound of Sound
	(properties
		number 36
	)
)

(instance egoInChair of Feature
	(properties
		x 160
		y 4
		z 100
		nsTop 87
		nsLeft 147
		nsBottom 121
		nsRight 174
	)
	
	(method (init)
		(super init: &rest)
		(directionHandler add: self)
	)
	
	(method (dispose)
		(directionHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (== (event message?) nullEvt))
				(== (theIconBar curIcon?) (theIconBar walkIconItem?))
				(& (event type?) direction)
			)
			(= reasonForLeaving reallyMustGo)
			(curRoom setScript: teleportOut)
			(event claimed: TRUE)
			(return)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_WALK)
			(= reasonForLeaving reallyMustGo)
			(curRoom setScript: teleportOut)
		else
			(egoChair doVerb: theVerb &rest)
		)
	)
)

(instance egoActions of Actions
	(method (doVerb theVerb)
		(return
			(if (== theVerb V_WALK)
				(= reasonForLeaving reallyMustGo)
				(curRoom setScript: teleportOut)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)
