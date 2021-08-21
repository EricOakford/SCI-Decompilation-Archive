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
(enum 1
	wontAnswerRiddles
	knowAllPunchlines
	thoughtsGiveYouAway
	notInMyHouse
	reallyMustGo
)

(local
	local0
	reasonForLeaving
	local2
	erasmusDrank
	erasmusDrinking
	local5
	local6
	local7
	jokesSaidYesTo
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
	local19
	[local20 10]
	[local30 7] = [0 11 12 -16 -5 -17 999]
	[local37 6] = [0 28 -4 -10 -6 999]
	[local43 5] = [0 20 27 19 999]
	[local48 6] = [0 21 13 -26 9 999]
	[local54 3] = [0 -23 999]
	[local57 3] = [0 -8 999]
	[local60 4] = [0 15 24 999]
	[local64 4] = [0 14 25 999]
	[local68 3] = [0 22 999]
	[local71 3] = [0 -7 999]
	[local74 3] = [0 18 999]
	[local77 15]
	[local92 12] = [0 -16 -5 -17 -10 -4 -6 -26 -23 -8 -7 999]
	[local104 4] = [0 12 11 999]
	[local108 6]
	[local114 2] = [0 999]
)
(instance rm31 of Room
	(properties
		picture 31
		style WIPEUP
	)
	
	(method (init)
		(= [local77 0] @local30)
		(= [local77 1] @local37)
		(= [local77 2] @local43)
		(= [local77 3] @local48)
		(= [local77 4] @local54)
		(= [local77 5] @local57)
		(= [local77 6] @local60)
		(= [local77 7] @local64)
		(= [local77 8] @local68)
		(= [local77 9] @local71)
		(= [local77 10] @local74)
		(= [local77 11] 999)
		(= [local108 0] @local104)
		(= [local108 1] 999)
		(walkHandler add: self)
		(LoadMany RES_VIEW 31 199 530 1034 1031)
		(LoadMany RES_SOUND 28 36)
		(Load RES_MESSAGE 31)
		(= local5 (Random 50 100))
		(= local6 100)
		(= local7 (Random 0 7))
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
;;;		(pillars init:)
;;;		(curtain init:)
;;;		(table init:)
;;;		(stairs init:)
;;;		(wWindow init:)
;;;		(fenrusPad init:)
;;;		(gStone init:)
;;;		(pStone init:)
;;;		(walls init:)
		
		(wizLegs addToPic:)
		(wizChair addToPic:)
		(egoChair addToPic:)
		(cup init:)
		(erasmusTeller init: wizard @local30 @local77 @local92)
		(wizard init: setPri: 15 actions: erasmusTeller)
		(if (== prevRoomNum 32)
			(fenrusTeller
				init: fenrusBody @local104 @local108 @local114
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
			(cSound number: 117 loop: -1 init: play:)
			(self setScript: intoTheTower)
		)
	)
	
	(method (doit)
		;added to address timer bug
		(if (< (Abs (- gameTime name)) 2) (return))
		(= name gameTime)
		(if (not (Btst 360))
			(if (== postMazeGame TRUE)
				(Palette PALCycle 232 246 -1)
				(Palette PALCycle 247 254 -1)
			)
			(if local0
				(= local0 0)
				(= local14 0)
				(= local13 0)
				(self setScript: teleportOut)
			)
			(cond 
				((curRoom script?))
				((and local13 (> local6 1)) (-- local6))
				((== local6 1)
					(if modelessDialog (modelessDialog dispose:))
					(HandsOff)
					(= local13 0)
					(= local14 0)
					(if (== local7 7) (= local7 0) else (++ local7))
					(switch local7
						(0
							(= local18 4)
							(= local19 31)
						)
						(1
							(= local18 3)
							(= local19 32)
						)
						(2
							(= local18 3)
							(= local19 33)
						)
						(3
							(= local18 3)
							(= local19 34)
						)
						(4
							(= local18 4)
							(= local19 35)
						)
						(5
							(= local18 2)
							(= local19 36)
						)
						(6
							(= local18 5)
							(= local19 37)
						)
						(7
							(= local18 3)
							(= local19 38)
						)
					)
					(self setScript: tellJoke)
				)
			)
			(cond 
				((curRoom script?))
				((and local14 (> local5 1)) (-- local5))
				((== local5 1)
					(= local5 (Random 450 750))
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
				(= reasonForLeaving 6)
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
	(properties)
	
	(method (showDialog)
		(super showDialog: -17 (Btst fWizKnowsEgoHasMagic))
	)
	
	(method (doChild)
		(return
			(if (== query -17)
				(super doChild: query)
				(return query)
			else
				(super doChild: query)
				(return query)
			)
		)
	)
	
	(method (doVerb theVerb param2)
		(egoHead setCel: 0)
		(switch theVerb
			(V_TALK
				(= local14 0)
				(= local13 0)
				(= local6 (+ local6 5))
				(SolvePuzzle POINTS_TALKTOERASMUS 1)
				(if erasmusDrinking
					(if askedErasmus
						(messager say: N_ROOM 0 C_WIZ_DRINKING 1)
					else
						(messager say: N_ROOM 0 C_WIZ_DRINKING 2)
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
		signal $4010
	)
	
	(method (doVerb theVerb param2)
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
		signal $4010
	)
)

(instance egoChair of View
	(properties
		x 161
		y 116
		view 31
		cel 2
		priority 5
		signal $4010
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
		signal $4010
	)
	
	(method (doVerb)
		(if erasmusDrank
			(messager say: N_CUP V_LOOK 0 1)
		else
			(messager say: N_CUP V_LOOK 0 2)
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
		(kernel_128 1034)
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
		(kernel_128 1031)
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
	(properties)
	
	(method (doVerb theVerb param2)
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
		signal $4000
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local14 0)
				(messager say: N_ROOM 0 local19 1 self)
			)
			(1
				1
				(switch
					(Print
						addButton: 0 10 0 0 2 0 19 31
						addButton: 1 10 0 0 1 0 0 31
						init:
					)
					(0 (self changeState: 3))
					(1 (self changeState: 2))
				)
			)
			(2
				(if (== local18 2)
					(messager say: N_ROOM 0 local19 2)
				else
					(messager say: N_ROOM 0 local19 3)
				)
				(if (!= local19 2) (++ jokesSaidYesTo))
				(if (== jokesSaidYesTo 7)
					(= reasonForLeaving knowAllPunchlines)
					(= local0 1)
				)
				(self changeState: 6)
			)
			(3
				3
				(if (== local18 2)
					(messager say: N_ROOM 0 local19 2)
					(self changeState: 6)
				else
					(messager say: N_ROOM 0 local19 2 self)
				)
			)
			(4
				4
				(if (== local18 3)
					(self changeState: 6)
				else
					(messager say: N_ROOM 0 local19 4 self)
				)
			)
			(5
				5
				(if (== local18 4)
					(self cue:)
				else
					(messager say: N_ROOM 0 local19 5 self)
				)
			)
			(6
				6
				(= local14 1)
				(= local13 1)
				(HandsOn)
				(= local6 (Random 300 400))
				(self dispose:)
			)
		)
	)
)

(instance intoTheTower of Script
	(properties)
	
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
				(messager say: N_ROOM 0 30 1 self)
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
				(messager say: N_ROOM 0 30 2 self)
				(ego setCel: 5 signal: (| (ego signal?) $0001))
				(egoHead posn: 161 88 show:)
			)
			(7
				(fenrusTeller
					init: fenrusBody @local104 @local108 @local114
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
				(messager say: N_ROOM 0 30 3 self)
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
				(messager say: N_ROOM 0 C_FIRST_MEETING 4 self)
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst fErasmusAskedMaze)
					(messager say: N_WIZARD 0 C_PRE_MAZE 12 self)
				else
					(messager say: N_WIZARD 0 C_PRE_MAZE 11 self)
				)
			)
			(1
				(switch
					(Print
						addButton: 0 10 0 0 2 0 0 31
						addButton: 1 10 0 0 1 0 19 31
						init:
					)
					(0 (self changeState: 2))
					(1 (self changeState: 4))
				)
			)
			(2
				(messager say: N_WIZARD 0 C_PRE_MAZE 13 self)
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fWizKnowsEgoHasMagic)
				(messager say: N_WIZARD 0 C_PRE_MAZE (+ 1 (* 2 local11)) self)
			)
			(1
				(switch
					(Print
						addButton: 0 10 0 0 2 0 0 31
						addButton: 1 10 0 0 1 0 19 31
						init:
					)
					(0 (self changeState: 2))
					(1 (self changeState: 4))
				)
			)
			(2
				(messager say: N_WIZARD 0 C_PRE_MAZE (+ 2 (* 2 local11)) self)
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
			(5 (messager say: N_WIZARD 0 C_PRE_MAZE 9 self))
			(6
				(switch
					(Print
						addButton: 0 10 0 0 2 0 0 31
						addButton: 1 10 0 0 1 0 19 31
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
					(messager say: N_WIZARD 0 C_PRE_MAZE 10 self)
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 60))
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
				(messager say: N_ROOM 0 C_POST_MAZE 1 self)
			)
			(3
				(ego signal: (| (ego signal?) $0001))
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
	(properties)
	
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
			(2 (= ticks 120))
			(3 (wizard setCycle: BegLoop self))
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self cue:)
				(doorSound play:)
			)
			(1
				(= postMazeGame 1)
				(door setCycle: EndLoop self)
			)
			(2 (= seconds 5))
			(3
				(doorSound stop:)
				(Bset fWizKnowsEgoHasMagic)
				(cSound fade:)
				(curRoom newRoom: 32)
			)
		)
	)
)

(instance teleportOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local6 0)
				(= local5 0)
				(wizard setScript: 0)
				(if (> reasonForLeaving notInMyHouse) (= reasonForLeaving notInMyHouse))
				(= ticks 30)
			)
			(1
				(messager say: 4 0 3 reasonForLeaving self)
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
				(curRoom drawPic: 400 -32761)
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
