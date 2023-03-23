;;; Sierra Script 1.0 - (do not remove this comment)
(script# 29)
(include game.sh) (include "29.shm")
(use Main)
(use Procs)
(use Print)
(use Talker)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm29 0
	gargoyleTalker 1
)

(local
	[local0 41]
	local41
	wornOut
	passedOut
	doorIsOpen
	local45
	local46
	gargoyleCued
	[local48 3]
	saveSortedFeatures
	saveSpeed
	theEyeIcon
	answer1
	answer2
	answer3
	answer4
	answer5
	local59
	local60
	local61
	local62
	local63
	[local64 60]
)
(procedure (ThatsNotNice)
	(= local45 1)
	(= gargoyleCued TRUE)
)

(instance rm29 of Room
	(properties
		noun N_ROOM
		picture 29
		style DISSOLVE
		horizon 157
		north 30
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						299 189
						212 168
						166 165
						143 136
						120 168
						4 182
						4 189
						0 189
					yourself:
				)
		)
		(LoadMany RES_SOUND 66 28)
		(LoadMany RES_VIEW 29 530)
		(self setFeatures: mySky doorPost theWindow)
		;UPGRADE
;;;		(mySky
;;;			init:
;;;			setOnMeCheck: ftrControl cBLUE
;;;		)
;;;		(doorPost
;;;			init:
;;;			setOnMeCheck: ftrControl cGREEN
;;;		)
;;;		(theWindow
;;;			init:
;;;			setOnMeCheck: ftrControl cCYAN
;;;		)
		
		(super init:)
		(theMusic number: 114 loop: -1 init: play:)
		(= saveSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(= saveSpeed speed)
		(StatusLine enable:)
		(gargoyle init: cycleSpeed: 2 stopUpd:)
		(theDoor cel: 0 posn: 113 118 init:)
		(= theEyeIcon (theIconBar at: ICON_LOOK))
		(theIconBar curIcon: theEyeIcon)
		(theGame setCursor: (theEyeIcon cursor?) TRUE)
		(ego setScript: walkIn)
	)
	
	(method (doit)
		(cond 
			((ego script?))
			((== (ego edgeHit?) SOUTH)
				(ego setScript: egoExits)
			)
			(gargoyleCued
				(= gargoyleCued FALSE)
				(gargoyle setScript: 0)
				(self setScript: teleportOut)
			)
			((and local41 (== (ego edgeHit?) SOUTH))
				(curRoom newRoom: 28)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(= useSortedFeatures saveSortedFeatures)
		(Bset fBeenIn29)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(messager say: N_ROOM V_TALK)
			)
			(V_OPEN
				(if doorIsOpen
					(messager say: N_ROOM V_OPEN)
				else
					(ThatsNotNice)
				)
			)
			(V_DETECT (ThatsNotNice))
			(V_ZAP (ThatsNotNice))
			(V_TRIGGER (ThatsNotNice))
			(V_DAZZLE (ThatsNotNice))
			(V_CALM (ThatsNotNice))
			(V_FETCH (ThatsNotNice))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mySky of Feature
	(properties
		noun N_SKY
		onMeCheck cBLUE
	)
)

(instance doorPost of Feature
	(properties
		noun N_DOOR
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(theDoor doVerb: theVerb &rest)
	)
)

(instance theWindow of Feature
	(properties
		noun N_WINDOW
		onMeCheck cCYAN
	)
)

(instance Magic of Prop
	(properties
		view 530
		cycleSpeed 2
	)
)

(instance theDoor of Prop
	(properties
		noun N_DOOR
		view 29
	)
)

(instance gargoyle of Actor
	(properties
		x 151
		y 60
		noun N_GARGOYLE
		view 29
		loop 2
		cel 2
		signal (| ignrAct ignrHrz)
	)
	
	(method (init)
		(= nightPalette 129)
		(PalVary PALVARYTARGET 129)
		(AssertPalette 29)
		(super init:)
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_LOOK
				(messager say: N_GARGOYLE V_LOOK)
			)
			(V_FLAME
				(= gargoyleCued TRUE)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gargoyleTalker of Talker
	(properties
		x 10
		y 10
		view 1029
		talkWidth 260
		modeless TRUE
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2029)
		(PalVary PALVARYTARGET 2029)
		(AssertPalette 1029)
		(= font userFont)
		(super init: gargBust gargMouth &rest)
	)
)

(instance gargBust of Prop
	(properties
		view 1029
		signal ignrAct
	)
)

(instance gargMouth of Prop
	(properties
		nsTop 29
		nsLeft 33
		view 1029
		loop 1
		signal ignrAct
	)
)

(instance portFrame of View
	(properties
		x 30
		y 76
		view 29
		loop 1
		priority 4
		signal (| ignrAct fixPriOn)
	)
)

(instance laffer of Prop
	(properties
		x 30
		y 76
		view 29
		loop 3
		priority 5
		signal (| ignrAct fixPriOn)
		cycleSpeed 8
	)
)

(instance walkIn of Script
	(method (changeState newState &tmp theSeq)
		(switch (= state newState)
			(0
				(ego init: posn: 142 233)
				(HandsOff)
				(= ticks 60)
			)
			(1
				(ChangeGait MOVE_WALK FALSE)
				(UseStamina 50)
				(cond 
					((< [egoStats STAMINA] 20)
						(= passedOut TRUE)
					)
					((< [egoStats STAMINA] 60)
						(= wornOut TRUE)
					)
				)
				(cond 
					;hero's super tired
					(passedOut
						(ego
							cycleSpeed: 12
							moveSpeed: 12
							view: 515
							setLoop: 2
							setMotion: MoveTo 148 183 self
						)
					)
					;hero's slightly tired
					(wornOut
						(ego
							cycleSpeed: 6
							moveSpeed: 6
							setMotion: MoveTo 148 183 self
						)
					)
					;hero's not worn out
					(else
						(ego setMotion: MoveTo 148 183 self)
					)
				)
			)
			(2
				(cond 
					(passedOut
						(ego
							setLoop: 1
							cel: 0
							cycleSpeed: 6
							moveSpeed: 6
							setCycle: CycleTo 4 1 self
						)
					)
					(wornOut
						(ego
							view: 515
							cycleSpeed: 6
							moveSpeed: 6
							setLoop: 0
							cel: 0
							setCycle: CycleTo 2 Forward
						)
						(= ticks 60)
					)
					(else (self cue:))
				)
			)
			(3
				(if passedOut
					(ego setCycle: EndLoop)
					(hitGround play: 90)
					(= ticks 240)
				else
					(self cue:)
				)
			)
			(4
				(cond 
					(passedOut
						(ego cycleSpeed: 6 setCycle: BegLoop)
						(= ticks 180)
					)
					(wornOut
						(ego cycleSpeed: 6 setCycle: EndLoop)
						(= ticks 120)
					)
					(else
						(self cue:)
					)
				)
			)
			(5
				(if passedOut
					(ego setLoop: 0 cel: 7 forceUpd:)
					(= ticks 6)
				else
					(self cue:)
				)
			)
			(6
				(NormalEgo loopN)
				(= ticks 6)
			)
			(7
				(= local41 1)
				(= theSeq (Random 1 4))
				(cond 
					((not (Btst fBeenIn29))
						(messager say: N_ROOM NULL C_FIRST_ENTRY 0 self)
					)
					(passedOut
						(messager say: N_ROOM NULL C_PASS_OUT 1 self)
					)
					(wornOut
						(messager say: N_ROOM NULL C_WORN_OUT 1 self)
					)
					(else
						(messager say: N_ROOM NULL C_RETURN_ENTRY theSeq self)
					)
				)
			)
			(8
				(HandsOff)
				(gargoyle cycleSpeed: 6 setCycle: BegLoop self)
			)
			(9
				(ego setSpeed: saveSpeed)
				(= ticks 90)
			)
			(10
				(cond 
					((not (Btst fBeenIn29))
						(messager say: N_GARGOYLE NULL C_FIRST_MEET_GARGOYLE 1 self)
					)
					(Night
						(messager say: N_GARGOYLE NULL C_NIGHT 1 self)
					)
					(passedOut
						(messager say: N_GARGOYLE NULL C_PASS_OUT 1 self)
					)
					(wornOut
						(messager say: N_GARGOYLE NULL C_WORN_OUT 1 self)
					)
					(else
						(messager say: N_GARGOYLE NULL C_HELLO_AGAIN 1 self)
					)
				)
			)
			(11
				(cond 
					((and Night (not (Btst fBeenIn29)))
						(messager say: N_GARGOYLE NULL C_NIGHT 1 self)
					)
					(Night
						(rm29 setScript: teleportOut)
						(self dispose:)
					)
					(passedOut
						(messager say: N_GARGOYLE NULL C_PASS_OUT_QUESTIONS 1 self)
					)
					((Btst fMetGargoyle)
						(messager say: N_GARGOYLE NULL C_RETURN_QUESTIONS 1 self)
					)
					(else
						(Bset fMetGargoyle)
						(messager say: N_GARGOYLE NULL C_ASK_QUEST 1 self)
					)
				)
			)
			(12
				(if (and Night (not (Btst fBeenIn29)))
					(rm29 setScript: teleportOut)
				else
					(rm29 setScript: askName)
				)
				(self dispose:)
			)
		)
	)
)

(instance egoExits of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) 245 self)
			)
			(1
				(curRoom newRoom: 28)
			)
		)
	)
)

(instance askName of Script
	(method (doit)
		(if (and (== state 6) (< (ego y?) 175))
			(= gargoyleCued TRUE)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp theY1 theY2 theY3)
		(switch (= state newState)
			(0
				0
				(= answer1 (Random 2 9))
				(= answer2 (Random 2 9))
				(= answer3 (Random 2 9))
				(= answer4 (Random 2 9))
				(messager say: N_GARGOYLE NULL C_ASK_NAME 1 self)
			)
			(1 1
				(self changeState: 5)
			)
			(2
				2
				(= answer2 (Random 2 9))
				(self changeState: 5)
			)
			(3
				3
				(= answer3 (Random 2 9))
				(self changeState: 5)
			)
			(4
				4
				(= answer4 (Random 2 9))
				(self changeState: 5)
			)
			(5
				5
				(cond 
					((== answer2 answer1)
						(self changeState: 2)
					)
					((or (== answer3 answer1) (== answer3 answer2))
						(self changeState: 3)
					)
					(
						(or
							(== answer4 answer1)
							(== answer4 answer2)
							(== answer4 answer3)
						)
						(self changeState: 4)
					)
					(else
						(self cue:)
					)
				)
			)
			(6
				6
				(if (> answer1 8)
					(= theY1 60)
				else
					(= theY1 40)
				)
				(if (> answer2 8)
					(= theY2 (+ theY1 40))
				else
					(= theY2 (+ theY1 20))
				)
				(if (> answer3 8)
					(= theY3 (+ theY2 40))
				else
					(= theY3 (+ theY2 20))
				)
				(switch
					;ego wasn't given a name
					(if (== userName NULL)
						(Print
							addButton: 0 N_GARGOYLE NULL C_ASK_NAME 10 0 0 29
							addButton: 1 N_GARGOYLE NULL C_ASK_NAME answer1 0 20 29
							addButton: 2 N_GARGOYLE NULL C_ASK_NAME answer2 0 theY1 29
							addButton: 3 N_GARGOYLE NULL C_ASK_NAME answer3 0 theY2 29
							addButton: 4 N_GARGOYLE NULL C_ASK_NAME answer4 0 theY3 29
							width: 170
							init:
						)
					else
						;ego has a name
						(Print
							addButton: 0 @userName 0 0
							addButton: 1 N_GARGOYLE NULL C_ASK_NAME answer1 0 20 29
							addButton: 2 N_GARGOYLE NULL C_ASK_NAME answer2 0 theY1 29
							addButton: 3 N_GARGOYLE NULL C_ASK_NAME answer3 0 theY2 29
							addButton: 4 N_GARGOYLE NULL C_ASK_NAME answer4 0 theY3 29
							width: 170
							init:
						)
					)
					(0
						(client setScript: askQuest)
						(self dispose:)
					)
					(1
						(= answer5 answer1)
						(self cue:)
					)
					(2
						(= answer5 answer2)
						(self cue:)
					)
					(3
						(= answer5 answer3)
						(self cue:)
					)
					(4
						(= answer5 answer4)
						(self cue:)
					)
				)
			)
			(7
				(messager say: N_GARGOYLE NULL C_WRONG_NAME answer5 self)
			)
			(8
				8
				(client setScript: teleportOut)
				(self dispose:)
			)
		)
	)
)

(instance askQuest of Script
	(method (doit)
		(if (and (== state 6) (< (ego y?) 175))
			(= gargoyleCued TRUE)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp theY1 theY2 theY3)
		(switch (= state newState)
			(0
				0
				(messager say: N_GARGOYLE NULL C_ASK_QUEST 2 self)
				(= answer1 (Random 1 2))
				(if [egoStats MAGIC]
					(= answer2 3)
				else
					(= answer2 (Random 4 9))
				)
				(= answer3 (Random 4 9))
				(= answer4 (Random 4 9))
				(= answer5 (Random 4 9))
			)
			(1 1 (self changeState: 5))
			(2
				2
				(= answer3 (Random 4 9))
				(self changeState: 5)
			)
			(3
				3
				(= answer4 (Random 4 9))
				(self changeState: 5)
			)
			(4
				(= answer5 (Random 4 9))
				(self changeState: 5)
			)
			(5
				5
				(cond 
					((== answer3 answer2)
						(self changeState: 2)
					)
					((or (== answer4 answer3) (== answer4 answer2))
						(self changeState: 3)
					)
					(
						(or
							(== answer5 answer4)
							(== answer5 answer3)
							(== answer5 answer2)
						)
						(self changeState: 4)
					)
					(else (self cue:))
				)
			)
			(6
				6
				(cond 
					((> answer2 7)
						(= theY1 68)
					)
					((> answer2 6)
						(= theY1 51)
					)
					(else
						(= theY1 34)
					)
				)
				(cond 
					((> answer3 7)
						(= theY2 (+ theY1 51))
					)
					((> answer3 6)
						(= theY2 (+ theY1 34))
					)
					(else
						(= theY2 (+ theY1 17))
					)
				)
				(cond 
					((> answer4 7)
						(= theY3 (+ theY2 51))
					)
					((> answer4 6)
						(= theY3 (+ theY2 34))
					)
					(else
						(= theY3 (+ theY2 17))
					)
				)
				(switch
					(Print
						addButton: 0 N_GARGOYLE NULL C_QUEST_ANSWERS answer1 0 0 29
						addButton: 1 N_GARGOYLE NULL C_QUEST_ANSWERS answer2 0 17 29
						addButton: 2 N_GARGOYLE NULL C_QUEST_ANSWERS answer3 0 theY1 29
						addButton: 3 N_GARGOYLE NULL C_QUEST_ANSWERS answer4 0 theY2 29
						addButton: 4 N_GARGOYLE NULL C_QUEST_ANSWERS answer5 0 theY3 29
						width: 150
						init:
					)
					(0
						(client setScript: askRand)
						(self dispose:)
					)
					(1
						(if [egoStats MAGIC]
							(client setScript: askRand)
							(self dispose:)
						else
							(= answer1 answer2)
							(self changeState: 7)
						)
					)
					(2
						(= answer1 answer3)
						(self changeState: 7)
					)
					(3
						(= answer1 answer4)
						(self changeState: 7)
					)
					(4
						(= answer1 answer5)
						(self changeState: 7)
					)
				)
			)
			(7
				7
				(messager say: N_GARGOYLE NULL C_WRONG_QUEST answer1 self)
			)
			(8
				8
				(client setScript: teleportOut)
				(self dispose:)
			)
		)
	)
)

(instance askRand of Script
	(method (doit)
		(if (and (== state 1) (< (ego y?) 175))
			(= gargoyleCued 1)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp temp0 temp1 temp2 temp3 temp4)
		(switch (= state newState)
			(0
				0
				(= local62 (Random 1 7))
				(= local61 1)
				(switch (= local59 (Random 0 4))
					(0
						(= answer1 1)
						(= answer2 2)
						(= answer3 3)
						(= answer4 4)
					)
					(1
						(= answer1 2)
						(= answer2 3)
						(= answer3 4)
						(= answer4 0)
					)
					(2
						(= answer1 3)
						(= answer2 4)
						(= answer3 0)
						(= answer4 1)
					)
					(3
						(= answer1 4)
						(= answer2 0)
						(= answer3 1)
						(= answer4 2)
					)
					(4
						(= answer1 0)
						(= answer2 1)
						(= answer3 2)
						(= answer4 3)
					)
				)
				(switch local62
					(1
						(= local63 14)
						(= local61 2)
					)
					(2
						(= local63 15)
						(= local61 2)
					)
					(3 (= local63 16))
					(4 (= local63 17))
					(5 (= local63 18))
					(6
						(= local63 19)
						(= local61 2)
					)
					(7 (= local63 20))
				)
				(messager say: N_GARGOYLE 0 local63 1 self)
			)
			(1
				1
				(cond 
					((== local62 6)
						(switch local59
							(0
								(= temp0 0)
								(= temp1 20)
								(= temp2 40)
								(= temp3 60)
								(= temp4 100)
								(= local60 0)
							)
							(1
								(= temp0 40)
								(= temp1 60)
								(= temp2 80)
								(= temp3 100)
								(= temp4 0)
								(= local60 4)
							)
							(2
								(= temp0 60)
								(= temp1 80)
								(= temp2 100)
								(= temp3 0)
								(= temp4 20)
								(= local60 3)
							)
							(3
								(= temp0 80)
								(= temp1 100)
								(= temp2 0)
								(= temp3 20)
								(= temp4 40)
								(= local60 2)
							)
							(4
								(= temp0 100)
								(= temp1 0)
								(= temp2 20)
								(= temp3 40)
								(= temp4 60)
								(= local60 1)
							)
						)
					)
					((== local62 7)
						(switch local59
							(0
								(= temp0 0)
								(= temp1 20)
								(= temp2 40)
								(= temp3 60)
								(= temp4 80)
								(= local60 0)
							)
							(1
								(= temp0 20)
								(= temp1 40)
								(= temp2 60)
								(= temp3 80)
								(= temp4 0)
								(= local60 4)
							)
							(2
								(= temp0 40)
								(= temp1 60)
								(= temp2 80)
								(= temp3 0)
								(= temp4 20)
								(= local60 3)
							)
							(3
								(= temp0 60)
								(= temp1 80)
								(= temp2 0)
								(= temp3 20)
								(= temp4 40)
								(= local60 2)
							)
							(4
								(= temp0 80)
								(= temp1 0)
								(= temp2 20)
								(= temp3 40)
								(= temp4 60)
								(= local60 1)
							)
						)
					)
					(else
						(switch local59
							(0
								(= temp0 0)
								(= temp1 20)
								(= temp2 40)
								(= temp3 60)
								(= temp4 80)
								(= local60 0)
							)
							(1
								(= temp0 20)
								(= temp1 40)
								(= temp2 60)
								(= temp3 80)
								(= temp4 0)
								(= local60 4)
							)
							(2
								(= temp0 40)
								(= temp1 60)
								(= temp2 80)
								(= temp3 0)
								(= temp4 20)
								(= local60 3)
							)
							(3
								(= temp0 60)
								(= temp1 80)
								(= temp2 0)
								(= temp3 20)
								(= temp4 40)
								(= local60 2)
							)
							(4
								(= temp0 80)
								(= temp1 0)
								(= temp2 20)
								(= temp3 40)
								(= temp4 60)
								(= local60 1)
							)
						)
					)
				)
				(switch
					(Print
						addButton: 0 N_GARGOYLE NULL local63 2 0 temp0 29
						addButton: 1 N_GARGOYLE NULL local63 3 0 temp1 29
						addButton: 2 N_GARGOYLE NULL local63 4 0 temp2 29
						addButton: 3 N_GARGOYLE NULL local63 5 0 temp3 29
						addButton: 4 N_GARGOYLE NULL local63 6 0 temp4 29
						width: 170
						first: local60
						init:
					)
					(0 (self changeState: 2))
					(1 (self changeState: 4))
					(2 (self changeState: 6))
					(3 (self changeState: 8))
					(4 (self changeState: 10))
				)
			)
			(2
				2
				(messager say: N_GARGOYLE NULL local63 7 self)
			)
			(3
				3
				(client setScript: letHimIn)
				(self dispose:)
			)
			(4
				4
				(messager say: N_GARGOYLE NULL local63 8 self)
			)
			(5
				5
				(if (== local61 2)
					(client setScript: letHimIn)
				else
					(client setScript: teleportOut)
				)
				(self dispose:)
			)
			(6
				6
				(messager say: N_GARGOYLE NULL local63 9 self)
			)
			(7
				7
				(client setScript: teleportOut)
				(self dispose:)
			)
			(8
				8
				(messager say: N_GARGOYLE NULL local63 10 self)
			)
			(9
				9
				(client setScript: teleportOut)
				(self dispose:)
			)
			(10
				10
				(messager say: N_GARGOYLE NULL local63 11 self)
			)
			(11
				11
				(client setScript: teleportOut)
				(self dispose:)
			)
		)
	)
)

(instance letHimIn of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_GARGOYLE NULL C_WIZARD_WILL_SEE 1 self)
			)
			(1
				(messager say: N_GARGOYLE NULL C_DIRECT_TO_TOWER 1 self)
			)
			(2
				(gargoyle cycleSpeed: 12 cel: 0 setCycle: EndLoop self)
			)
			(3
				(gargoyle cycleSpeed: 6 setCycle: BegLoop self)
			)
			(4
				(gargoyle cycleSpeed: 6 cel: 0 setCycle: EndLoop)
				(= doorIsOpen TRUE)
				(doorOpenSound play:)
				(theDoor cycleSpeed: (* howFast 6) setCycle: EndLoop self)
			)
			(5
				(theDoor ignoreActors: stopUpd:)
				(HandsOn)
				(User canControl: TRUE canInput: TRUE)
				(NormalEgo loopN)
				(gargoyle setCycle: EndLoop self)
			)
			(6 (self dispose:))
		)
	)
)

(instance teleportOut of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 30)
			)
			(1
				(switch (Random 0 6)
					(0
						(messager say: N_GARGOYLE NULL C_WARP_OUT 1)
						(= ticks 360)
					)
					(1
						(messager say: N_GARGOYLE NULL C_WARP_OUT 2)
						(= ticks 360)
					)
					(2
						(messager say: N_GARGOYLE NULL C_WARP_OUT 3)
						(= ticks 360)
					)
					(3
						(messager say: N_GARGOYLE NULL C_WARP_OUT 4)
						(= ticks 360)
					)
					(4
						(messager say: N_GARGOYLE NULL C_WARP_OUT 5)
						(= ticks 420)
					)
					(5
						(messager say: N_GARGOYLE NULL C_WARP_OUT 6)
						(= ticks 300)
					)
					(6
						(messager say: N_GARGOYLE NULL C_WARP_OUT 7)
						(= ticks 300)
					)
				)
			)
			(2
				(gargoyle setCycle: EndLoop self)
			)
			(3
				(gargoyle cycleSpeed: 1 setCycle: BegLoop self)
			)
			(4
				(portFrame init: stopUpd:)
				(laffer init: setCycle: Forward)
				(gargoyle setCycle: Forward)
				(Magic
					ignoreActors:
					setPri: (+ (ego priority?) 1)
					cycleSpeed: 2
					posn:
						(if
						(and (== (ego view?) 515) (== (ego loop?) 0))
							(+ (ego x?) 5)
						else
							(ego x?)
						)
						(ego y?)
					init:
					setCycle: CycleTo 5 1 self
				)
			)
			(5
				(teleportSound play:)
				(ego hide:)
				(Magic setCycle: CycleTo 3 -1 self)
			)
			(6
				(Magic setCycle: EndLoop)
				(= ticks (Random 90 180))
			)
			(7
				(Bset fErasmusWarpOut)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 (| BLACKOUT IRISOUT))
				(self cue:)
			)
			(8 (curRoom newRoom: 28))
		)
	)
)

(instance teleportSound of Sound
	(properties
		number 28
	)
)

(instance hitGround of Sound
	(properties
		number 66
	)
)

(instance doorOpenSound of Sound
	(properties
		number 89
	)
)
