;;; Sierra Script 1.0 - (do not remove this comment)
(script# 340)
(include game.sh) (include "340.shm")
(use Main)
(use GloryWindow)
(use OccasionalCycle)
(use GloryTalker)
(use Messager)
(use Talker)
(use IconBar)
(use LoadMany)
(use GControl)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm340 0
	speakerTalker 1
	warriorTalker 2
	proc340_3 3
	motherTalker 4
	haramiTalker 5
)

(local
	sworeOath
	yesText
	noText
)
(procedure (proc340_3)
	(ego hold: 1 3 110)
	;(super init: &rest)	;this will not compile, but the procedure doesn't seem to be used anyway
	(self setScript: doEvent2)
)

(procedure (InitCast)
	(rajah init:)
	(yWarrior init:)
	(mother init:)
	(youth init:)
	(kreesha init:)
	(priestess init:)
	(warriorTail
		init:
		setCycle: OccasionalCycle self 1 70 180
	)
	(speaker init:)
	(speakerStuff
		init:
		setCycle: OccasionalCycle self 1 70 180
	)
)

(procedure (SwearOath &tmp len oldCur)
	(= oldCur theCursor)
	(= len (Message MsgSize 340 N_OATH V_QUESTION C_YES 1))
	(= yesText (Memory MNewPtr len))
	(Message MsgGet 340 N_OATH V_QUESTION C_YES 1 yesText)
	(= len (Message MsgSize 340 N_OATH V_QUESTION C_NO 1))
	(= noText (Memory MNewPtr len))
	(Message MsgGet 340 N_OATH V_QUESTION C_NO 1 noText)
	(quest init: show: dispose:)
	(Memory MDisposePtr yesText)
	(Memory MDisposePtr noText)
	(theGame setCursor: oldCur)
)

(class Head of View
	(properties
		face 2
	)
	
	(method (init)
		(= face cel)
		(super init: &rest)
	)
	
	(method (back)
		(heads eachElementDo: #setCel (self face?))
		(Animate (heads elements?) FALSE)
	)
	
	(method (cue theCel)
		(heads eachElementDo: #setCel theCel)
		(Animate (heads elements?) FALSE)
	)
)

(instance myMessager of Messager
	
	(method (say who)
		(switch who
			(5 (speaker cue: 0))
			(8 (priestess cue: 1))
			(9 (mother cue: 2))
			(1 (speaker cue: 3))
			(7 (kreesha cue: 5))
			(4 (yWarrior cue: 6))
			(else  (speaker back:))
		)
		(messager say: who &rest)
	)
)

(instance heads of List)

(instance rm340 of Room
	(properties
		picture 340
	)
	
	(method (init)
		(HandsOff)
		(heads
			add: priestess mother speaker youth kreesha yWarrior
			init:
		)
		((Prop new:)
			view: 340
			loop: 0
			cel: 1
			x: 103
			y: 41
			init:
			setCycle: Forward
		)
		((Prop new:)
			view: 340
			loop: 0
			cel: 1
			x: 210
			y: 41
			init:
			setCycle: Forward
		)
		(cond 
			((or (== prevRoomNum 230) (== prevRoomNum 240))
				(Bset fHaramiWasOnTrial)
				(InitCast)
				(ego
					view: 5
					loop: 6
					yStep: 2
					x: 28
					y: 240
					init:
					setCycle: Walk
					setScale: 175
				)
				(localHarami init: setScale: 175)
				(super init: &rest)
				(self setScript: doEvent1)
			)
			((and (== prevRoomNum 310) (not (Btst fSoulJudged)))
				(cSound setLoop: -1 number: 340 play: 127)
				(Bset fRakeeshSworeOath)
				(InitCast)
				(rajah init: stopUpd:)
				((ScriptID RAKEESH_TALKER 1)
					loop: 3
					x: 162
					y: 165
					init:
					setScale: 175
				)
				(ego x: 185 y: 169 loop: 3 init: setScale: 175 normalize:)
				(super init: &rest)
				(self setScript: doEvent2)
			)
			(else
				(cSound setLoop: -1 number: 340 play: 127)
				(InitCast)
				((ScriptID RAKEESH_TALKER 1)
					x: 30
					y: 213
					loop: 3
					init:
					setScale: 175
					addToPic:
				)
				(ego
					x: 50
					y: 223
					setCel: 0
					loop: 3
					yStep: 4
					init:
					normalize:
					setScale: 175
					solvePuzzle: 252 20
				)
				(leopardman init: stopUpd:)
				(simbani init: stopUpd:)
				(yesufU init: setScale: 175)
				(super init: &rest)
				(self setScript: doEvent3)
			)
		)
	)
	
	(method (dispose)
		(LoadMany FALSE RAKEESH_TALKER LAIBON_TALKER PRIESTESS_TALKER KREESHA_TALKER)
		(heads dispose:)
		(super dispose:)
	)
)

(instance doEvent1 of Script
	
	(method (changeState newState &tmp knockedFruit)
		(switch (= state newState)
			(0
				(if (!= egoGait MOVE_WALK)
					(ego changeGait: MOVE_WALK FALSE)
				)
				(= cycles 10)
			)
			(1
				(messager say: N_EGO V_DOIT C_JUDGEMENT1 0 self)
			)
			(2
				(myMessager say: N_ROOM V_DOIT C_JUDGEMENT1 0 rajah)
			)
			(3
				(myMessager say: N_HARAMI V_DOIT C_JUDGEMENT3 0 rajah)
			)
			(4
				(myMessager say: N_ROOM V_DOIT C_JUDGEMENT4 0 rajah)
			)
			(5
				(localHarami
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo (localHarami x?) 215 self
				)
			)
			(6
				(localHarami dispose:)
				(myMessager say: N_ROOM V_DOIT C_JUDGEMENT5 0 rajah)
			)
			(7
				(messager say: N_ROOM V_DOIT C_JUDGEMENT6)	;EO: Added in to restore an unused message
				(ego
					view: 0
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo (ego x?) (+ (ego y?) 35) self
				)
			)
			(8
				(ego normalize:)
				(= seconds 1)
			)
			(9
				(ego posn: 150 260 setMotion: MoveTo 150 180 self)
			)
			(10
				(= knockedFruit FALSE)
				(cond 
					((Btst fTrippedHarami)
						(= knockedFruit TRUE)
					)
					((and (Btst fDaggeredHarami) (not (Btst fDidntCatchHarami)))
						(curRoom setScript: thiefSolution)
					)
					((or (Btst fUsedCalmOnHarami) (Btst fFlamedHarami))
						(curRoom setScript: magicSolution)
					)
					((Btst fRanAfterHarami)
						(curRoom setScript: chaseHarami)
					)
					(else
						(myMessager say: N_ROOM V_DOIT C_DIDNT_STOP_HARAMI 0 rajah)
					)
				)
				(if knockedFruit
					(curRoom setScript: doEvent1Alt self)
				)
			)
			(11
				(myMessager say: N_SPEAKER V_DOIT C_CHASE_HARAMI3 0 rajah)
			)
			(12
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance chaseHarami of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(myMessager say: N_WARRIOR V_DOIT C_CHASE_HARAMI1 0 rajah)
			)
			(1
				(myMessager say: N_ROOM V_DOIT C_CHASE_HARAMI2 0 rajah)
			)
			(2
				(myMessager say: N_SPEAKER V_DOIT C_CHASE_HARAMI3 0 rajah)
			)
			(3
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance thiefSolution of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(myMessager say: N_WARRIOR V_DOIT C_THIEF1 0 rajah)
			)
			(1
				(myMessager say: N_ROOM V_DOIT C_THIEF2 0 rajah)
			)
			(2
				(myMessager say: N_PRIESTESS V_DOIT C_THIEF3 0 rajah)
			)
			(3
				(myMessager say: N_ROOM V_DOIT C_THIEF4 0 rajah)
			)
			(4
				(myMessager say: N_SPEAKER V_DOIT C_THIEF5 0 rajah)
			)
			(5
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance magicSolution of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(myMessager say: N_WARRIOR V_DOIT C_MAGIC1 0 rajah)
			)
			(1
				(myMessager say: N_ROOM V_DOIT C_MAGIC2 0 rajah)
			)
			(2
				(myMessager say: N_KREESHA V_DOIT C_MAGIC3 0 rajah)
			)
			(3
				(if (Btst fDidntCatchHarami)
					(messager say: N_WARRIOR V_DOIT C_CALMED_HARAMI 0 rajah)
				else
					(curRoom setScript: solution2)
				)
			)
			(4
				(curRoom setScript: forbidden)
			)
		)
	)
)

(instance solution2 of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst fUsedCalmOnHarami)
					(messager say: N_WARRIOR V_DOIT C_CALMED_HARAMI 0 rajah)
				else
					(myMessager say: N_WARRIOR V_DOIT C_MAGIC4 0 rajah)
				)
			)
			(1
				(if (Btst fUsedCalmOnHarami)
					(curRoom setScript: forbidden)
				else
					(myMessager say: N_PRIESTESS V_DOIT C_MAGIC4 0 rajah)
				)
			)
			(2
				(myMessager say: N_ROOM V_DOIT C_MAGIC4 0 rajah)
			)
			(3
				(curRoom setScript: forbidden)
			)
		)
	)
)

(instance forbidden of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(myMessager say: N_ROOM V_DOIT C_FORBIDDEN1 0 rajah)
			)
			(1
				(myMessager say: N_KREESHA V_DOIT C_FORBIDDEN2 0 rajah)
			)
			(2
				(myMessager say: N_ROOM V_DOIT C_FORBIDDEN3 0 rajah)
			)
			(3
				(messager say: N_EGO V_DOIT C_FORBIDDEN4 0 self)
			)
			(4
				(myMessager say: N_ROOM V_DOIT C_FORBIDDEN5 0 rajah)
			)
			(5
				(myMessager say: N_SPEAKER V_DOIT C_THIEF5 0 rajah)
			)
			(6
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance doEvent1Alt of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(myMessager say: N_WARRIOR V_DOIT C_KNOCKED_FRUIT1 0 rajah)
				((ScriptID RAKEESH_TALKER 1)
					loop: 3
					x: 180
					y: 280
					init:
					setScale: 175
				)
			)
			(1
				(myMessager say: N_ROOM V_DOIT C_KNOCKED_FRUIT2 0 rajah)
			)
			(2
				(myMessager say: N_RAKEESH V_DOIT C_KNOCKED_FRUIT3 0 rajah)
			)
			(3
				((ScriptID RAKEESH_TALKER 1)
					setCycle: Walk
					setMotion: MoveTo 190 185 self
				)
			)
			(4
				(myMessager say: N_ROOM V_DOIT C_KNOCKED_FRUIT4 0 rajah)
			)
			(5
				(myMessager say: N_SPEAKER V_DOIT C_KNOCKED_FRUIT5 0 rajah)
			)
			(6
				(myMessager say: N_RAKEESH V_DOIT C_KNOCKED_FRUIT6 0 rajah)
			)
			(7
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance doEvent2 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr fInMainGame)
				(= cycles 10)
			)
			(1
				(myMessager say: N_ROOM V_DOIT C_HONOR1 0 rajah)
			)
			(2
				(myMessager say: N_WARRIOR V_DOIT C_HONOR2 0 rajah)
			)
			(3
				(myMessager say: N_PRIESTESS V_DOIT C_HONOR3 0 rajah)
			)
			(4
				(myMessager say: N_KREESHA_9 V_DOIT C_HONOR4 0 rajah)
			)
			(5
				(myMessager say: N_WARRIOR V_DOIT C_HONOR5 0 rajah)
			)
			(6
				(myMessager say: N_RAKEESH V_DOIT C_HONOR6 0 rajah)
			)
			(7
				(myMessager say: N_KREESHA V_DOIT C_HONOR6 0 rajah)
			)
			(8
				(myMessager say: N_RAKEESH V_DOIT C_HONOR7 0 rajah)
			)
			(9
				(myMessager say: N_ROOM V_DOIT C_HONOR8 0 rajah)
			)
			(10
				(SwearOath)
				(if sworeOath
					(ego addHonor: 40 solvePuzzle: fSwearOath 5 puzzlePALADIN)
				else
					(ego addHonor: -100)
					(Bset fCantBePaladin)
				)
				(= cycles 1)
			)
			(11
				(myMessager say: N_ROOM V_DOIT C_HONOR9 0 rajah)
			)
			(12
				(myMessager say: N_SPEAKER V_DOIT C_HONOR10 0 rajah)
			)
			(13
				(Bset fMetRajah)
				(Bset fInMainGame)
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance doEvent3 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(yesufU setScale:)
				((ScriptID RAKEESH_TALKER 1) setScale:)
				(= cycles 10)
			)
			(1
				(messager say: N_EGO V_DOIT C_CONFERENCE1 0 self)
				(yesufU stopUpd:)
			)
			(2
				(myMessager say: N_ROOM V_DOIT C_CONFERENCE2 0 rajah)
			)
			(3
				(myMessager say: N_LAIBON V_DOIT C_CONFERENCE3 0 rajah)
			)
			(4
				(simbani startUpd: loop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(5
				(if (Btst fSenseDanger)
					(messager say: N_EGO V_DOIT C_CONFERENCE4 0 self)
				else
					(= cycles 1)
				)
			)
			(6
				(myMessager say: N_RAKEESH V_DOIT C_CONFERENCE5 0 rajah)
			)
			(7
				(leopardman
					startUpd:
					loop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(8
				(= seconds 1)
			)
			(9
				(leopardman loop: 1 setCel: 0 setCycle: EndLoop self)
				(globalSound number: 900 play: 127)
			)
			(10
				(leopardman setCel: 0)
				(yesufU forceUpd:)
				(= cycles 1)
			)
			(11
				(simbani loop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(12
				(yesufU view: 984 setLoop: 3 setCel: 0 setCycle: EndLoop self)
				(globalSound number: 916 play: 127)
				(simbani stopUpd:)
			)
			(13
				(leopardman setLoop: 2 setCel: 0 setCycle: EndLoop self)
				(globalSound number: 920 play: 127)
				(yesufU stopUpd:)
			)
			(14
				(leopardman setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(15
				(= seconds 2)
			)
			(16
				(leopardman setLoop: 4 setCel: 0 setCycle: EndLoop self)
				(globalSound number: 918 play: 127)
			)
			(17
				(myMessager say: N_RAKEESH V_DOIT C_CONFERENCE6 0 self)
			)
			(18
				(rajah startUpd: setCycle: EndLoop self)
				(leopardman stopUpd:)
			)
			(19
				(messager say: N_SPEAKER V_DOIT C_CONFERENCE7 0 self)
				(rajah stopUpd:)
			)
			(20
				(messager say: N_RAKEESH V_DOIT C_CONFERENCE8 0 self)
			)
			(21
				(ego
					view: 1
					setLoop: 4
					setCycle: Walk
					setMotion: MoveTo (+ (ego x?) 5) (+ (ego y?) 10) self
				)
			)
			(22
				(Bset fAfterConference)
				(curRoom newRoom: 210)
			)
		)
	)
)

(instance rajah of Prop
	(properties
		x 160
		y 79
		view 334
	)
	
	(method (cue)
		(speaker back:)
		(curRoom cue:)
	)
)

(instance localHarami of Actor
	(properties
		x 162
		y 152
		view 950
		loop 3
		signal ignrAct
	)
)

(instance leopardman of Prop
	(properties
		x 123
		y 165
		view 346
		loop 1
		signal ignrAct
	)
)

(instance simbani of Prop
	(properties
		x 196
		y 169
		view 347
	)
)

(instance yesufU of Prop
	(properties
		x 261
		y 189
		view 980
		loop 4
		cel 3
	)
)

(instance yWarrior of Head
	(properties
		x 240
		y 131
		view 335
		loop 1
		cel 1
		priority 15
		signal fixPriOn
	)
)

(instance warriorTail of Prop
	(properties
		x 273
		y 145
		view 335
	)
)

(instance speaker of Head
	(properties
		x 140
		y 91
		view 337
		loop 1
		cel 5
	)
)

(instance speakerStuff of Prop
	(properties
		x 146
		y 89
		view 337
		cel 4
	)
)

(instance mother of Head
	(properties
		x 117
		y 107
		view 341
		cel 1
	)
)

(instance youth of Head
	(properties
		x 184
		y 94
		view 344
		cel 1
	)
)

(instance kreesha of Head
	(properties
		x 202
		y 107
		view 348
		cel 1
		priority 14
		signal fixPriOn
	)
)

(instance priestess of Head
	(properties
		x 75
		y 131
		view 357
		cel 5
		priority 14
		signal fixPriOn
	)
)

(instance haramiTalker of GloryTalker
	(properties
		x 200
		y 2
		view 951
		loop 1
		talkWidth 260
		back 57
		textX -175
		textY 150
		backColor 26
	)
	
	(method (init)
		(super init: haramiBust haramiEyes haramiMouth &rest)
	)
)

(instance haramiMouth of Prop
	(properties
		nsTop 45
		nsLeft 26
		view 951
	)
)

(instance haramiEyes of Prop
	(properties
		nsTop 30
		nsLeft 24
		view 951
		loop 2
	)
)

(instance haramiBust of View
	(properties
		nsTop 20
		nsLeft 23
		view 951
		loop 3
	)
)

(instance speakerTalker of GloryTalker
	(properties
		x 5
		y 2
		view 338
		loop 1
		talkWidth 260
		back 57
		textX 20
		textY 150
		backColor 50
	)
	
	(method (init)
		(super init: speakerBust speakerEyes speakerMouth &rest)
	)
)

(instance speakerMouth of Prop
	(properties
		nsTop 47
		nsLeft 31
		view 338
	)
)

(instance speakerEyes of Prop
	(properties
		nsTop 38
		nsLeft 34
		view 338
		loop 2
	)
)

(instance speakerBust of View
	(properties
		nsTop 28
		nsLeft 33
		view 338
		loop 3
	)
)

(instance warriorTalker of GloryTalker
	(properties
		x 5
		y 2
		view 336
		loop 1
		talkWidth 260
		back 57
		textX 20
		textY 150
		backColor 9
	)
	
	(method (init)
		(super init: warriorBust warriorEyes warriorMouth &rest)
	)
)

(instance warriorMouth of Prop
	(properties
		nsTop 57
		nsLeft 46
		view 336
	)
)

(instance warriorEyes of Prop
	(properties
		nsTop 40
		nsLeft 44
		view 336
		loop 2
	)
)

(instance warriorBust of View
	(properties
		nsTop 28
		nsLeft 48
		view 336
		loop 3
	)
)

(instance motherTalker of Narrator
	(properties
		back 57
	)
)

(instance quest of GameControls
	
	(method (init)
		(theGame setCursor: ARROW_CURSOR)
		((= window (GloryWindow new:))
			top: 60
			left: 95
			bottom: 105
			right: 225
			priority: 15
			yourself:
		)
		(self add: titleIcon yesIcon noIcon)
		(super init: &rest)
	)
)

(instance titleIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 0
		signal DISABLED
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [str 20])
		(Message MsgGet 340 N_OATH V_QUESTION C_TITLE 1 @str)
		(Display @str
			p_at 5 3
			p_color 17
		)
	)
)

(instance yesIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 15
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show)
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display yesText
			p_at 20 (+ nsTop 3)
			p_color 17
		)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= sworeOath TRUE)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp sColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= sColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= sColor 17)
		)
		(Display yesText
			p_at 20 (+ nsTop 3)
			p_color sColor
		)
	)
)

(instance noIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 30
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [str 15])
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display noText
			p_at 20 (+ nsTop 3)
			p_color 17
		)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= sworeOath FALSE)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp sColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= sColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= sColor 17)
		)
		(Display noText
			p_at 20 (+ nsTop 3)
			p_color sColor
		)
	)
)
