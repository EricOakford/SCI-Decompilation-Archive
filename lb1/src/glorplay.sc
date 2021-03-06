;;; Sierra Script 1.0 - (do not remove this comment)
(script# 233)
(include game.sh)
(use Main)
(use servent)
(use atsgl)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	glorplay 0
)
(synonyms
	(actress person girl)
)

(local
	local0
	local1 = [220 221 225 226]
)
(instance Jeeves of servent)

(instance glorplay of Region

	(method (init)
		(super init:)
		(LoadMany SOUND 99 220 221 225 226)
		(LoadMany 143 243)
		(if (== currentAct 2)
			(if (not (& global118 $0008))
				(LoadMany FONT 41)
				(LoadMany SOUND 29 94 95 96)
				(Load VIEW 642)
				(Load SCRIPT 406)
			)
			(Load VIEW 902)
			(LoadMany 143 258)
			(= [global377 2] 258)
			(= global199 2)
		)
		(= global208 4)
		(if (not (& global145 $0002))
			(Load SCRIPT AVOIDER)
		)
		(Gloria
			view: 370
			loop: 0
			illegalBits: 0
			posn: 293 94
			stopUpd:
		)
		(Arm setPri: 6 init:)
		(Ash setPri: 6 init:)
		(Head setPri: 6 init:)
		(Smoke setPri: 6 init:)
		(self setScript: Smoking)
		(Gloria init:)
		(Leg init: setScript: crossLeg)
		(record init: setScript: playRecord)
		(if (and (== currentAct 0) (not (& global194 $0004)))
			(|= global194 $0004)
			(Jeeves
				view: 444
				posn: 320 98
				setCycle: Walk
				guest1: Gloria
				exitX: 320
				exitY: 98
				itemX: 295
				itemY: 100
				setAvoider: ((Avoider new:) offScreenOK: TRUE)
				init:
			)
			(= global167 1)
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(if (< global159 3)
			(++ global159)
		else
			(= global159 0)
		)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(DisposeScript SAVE)
		(if (== (event type?) saidEvent)
			(cond 
				(
					(or
						(Said 'play,attach,change/music,record[/(gramophone,(player<record))<on]')
						(Said '(rotate<on,off),(wind<up)/gramophone,(player<record)')
					)
					(if haveInvItem
						(Print 233 0)
					else
						(Print 233 1)
					)
				)
				((Said '/boa>')
					(cond 
						((Said 'examine')
							(Print 233 2)
						)
						((Said 'get')
							(Print 233 3)
						)
					)
				)
				((Said '/cigarette>')
					(cond 
						((Said 'examine')
							(Print 233 4)
						)
						((Said 'smoke')
							(Print 233 5)
						)
						((Said 'get')
							(Print 233 6)
						)
					)
				)
				((and (<= currentAct 1) (Said 'deliver,hold/*'))
					(if (and theInvItem haveInvItem)
						(Print 233 7)
					else
						(DontHave)
					)
				)
			)
		)
	)
)

(instance Smoking of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== currentAct 2)
					(cond 
						((not global216)
							(= state -1)
						)
						((not (& global118 $0008))
							(|= global118 $0008)
							(self setScript: (ScriptID 406 0))
							(= state -1)
						)
						((self script?)
							(= state -1)
						)
					)
				)
				(= cycles 1)
			)
			(1
				(Arm cycleSpeed: 1 loop: 1 cel: 0 setCycle: EndLoop)
				(= seconds 3)
			)
			(2
				(Arm setCycle: BegLoop self)
			)
			(3
				(Smoke setCycle: EndLoop)
				(if (< (Random 1 100) 30)
					(= state 6)
				)
				(= seconds (Random 6 12))
			)
			(4
				(Arm loop: 2 setCycle: EndLoop self)
			)
			(5
				(Arm loop: 3 setCycle: Forward)
				(if (!= (Gloria script?) goSee)
					(Ash show: setCycle: EndLoop self)
				)
			)
			(6
				(Ash hide:)
				(Arm loop: 2 cel: 2 setCycle: BegLoop)
				(= seconds (Random 6 12))
				(= state 0)
			)
			(7
				(Head setCycle: EndLoop)
				(= seconds (Random 6 12))
			)
			(8
				(Head setCycle: BegLoop)
				(= seconds (Random 6 12))
				(= state 0)
			)
		)
	)
)

(instance goSee of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cls)
				(HandsOff)
				(if (ego inRect: 43 113 100 125)
					(ego
						setMotion: MoveTo (+ (ego x?) 20) (+ (ego y?) 20)
					)
				)
				(Gloria
					view: 360
					setCycle: Walk
					setAvoider: (Avoider new:)
					setMotion: MoveTo 72 118 self
				)
				(Leg hide:)
				(Arm hide:)
				(Head hide:)
				(Ash hide:)
				(Smoke hide:)
			)
			(1
				(gDoor setCycle: EndLoop self)
				(gMySound setCycle: EndLoop)
			)
			(2
				(Gloria illegalBits: 0 setMotion: MoveTo 35 118 self)
			)
			(3
				(gDoor setCycle: BegLoop)
				(gMySound setCycle: BegLoop self)
			)
			(4
				(= seconds 5)
			)
			(5
				(gDoor setCycle: EndLoop self)
				(gMySound setCycle: EndLoop)
			)
			(6
				(if (ego inRect: 271 84 320 98)
					(ego setMotion: MoveTo 268 104)
				)
				(Gloria setMotion: MoveTo 72 118 self)
			)
			(7
				(cls)
				(gDoor setCycle: BegLoop self)
				(gMySound setCycle: BegLoop)
			)
			(8
				(= theTalker talkGLORIA)
				(Say 1 233 8)
				(gDoor stopUpd:)
				(gMySound stopUpd:)
				(Gloria setMotion: MoveTo 293 94 self)
			)
			(9
				(Gloria view: 370 loop: 0 setAvoider: 0)
				(DisposeScript AVOIDER)
				(Arm show:)
				(Head show:)
				(Leg show:)
				(Ash show:)
				(Smoke show:)
				(HandsOn)
				(client setScript: Smoking)
			)
		)
	)
)

(instance crossLeg of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Leg setCycle: EndLoop)
				(= seconds (Random 6 12))
			)
			(1
				(Leg setCycle: BegLoop)
				(= seconds (Random 12 18))
				(= state -1)
			)
		)
	)
)

(instance Gloria of Actor
	(properties
		y 134
		x 187
		view 369
	)
	
	(method (handleEvent event &tmp temp0)
		(= theTalker talkGLORIA)
		(cond 
			(
				(and
					(Btst fSawDeadGuest)
					(Said 'tell[/actress]/(death<gertie),gertie<about')
				)
				(if (& deadGuests deadGERTRUDE)
					(if (& global145 $0002)
						(= temp0
							(switch currentAct
								(2 50)
								(else  43)
							))
						(= global212 2)
						(= global209 event)
						(proc243_1 temp0 233 9)
					else
						(DisposeScript SAVE)
						(|= global145 $0002)
						(Say 1 233 10)
						(Gloria setScript: goSee)
					)
				else
					(Say 1 233 11)
				)
			)
			((and (<= currentAct 1) (Said 'ask,tell//*<about'))
				(Print 233 7)
			)
			((and (<= currentAct 1) (Said 'deliver,hold'))
				(if (and theInvItem haveInvItem)
					(Print 233 7)
				else
					(DontHave)
				)
				(event claimed: TRUE)
			)
			(
				(and
					(not (& global207 $0004))
					(or
						(MousedOn self event shiftDown)
						(Said 'examine/actress[/!*]')
					)
				)
				(event claimed: TRUE)
				(|= global207 $0004)
				(Say 0 233 12)
			)
			(
				(and
					(& global207 $0004)
					(or
						(MousedOn self event shiftDown)
						(Said 'examine/actress[/!*]')
					)
				)
				(event claimed: TRUE)
				(if (== currentAct 2)
					(Print 233 13)
				else
					(Print 233 14)
				)
			)
			((Said '/actress>')
				(cond 
					((Said 'hear')
						(Print 233 15)
					)
					((Said 'converse')
						(if (== currentAct 2)
							(switch global172
								(0 (Say 1 233 16))
								(1 (Say 1 233 17))
								(2 (Say 1 233 18))
								(else  (Print 233 19))
							)
							(++ global172)
						else
							(Print 233 7)
						)
					)
					((Said 'get')
						(Print 233 20)
					)
					((Said 'kill')
						(Print 233 21)
					)
					((Said 'kiss')
						(Print 233 22)
					)
					((Said 'embrace')
						(Print 233 23)
					)
				)
			)
		)
	)
)

(instance Arm of Prop
	(properties
		y 56
		x 289
		view 370
		loop 1
	)
)

(instance Smoke of Prop
	(properties
		y 54
		x 292
		view 370
		loop 6
	)
)

(instance Ash of Prop
	(properties
		y 89
		x 274
		view 370
		loop 7
	)
)

(instance Head of Prop
	(properties
		y 56
		x 294
		view 370
		loop 4
	)
)

(instance Leg of Prop
	(properties
		y 94
		x 293
		view 370
		loop 8
	)
)

(instance recordMusic of Sound)

(instance record of Prop
	(properties
		y 67
		x 98
		view 136
		loop 5
		priority 6
		signal fixPriOn
	)
)

(instance playRecord of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 1)
				(record setCycle: Forward)
				(recordMusic
					number: [local1 global159]
					loop: 1
					play: self
				)
			)
			(1
				(recordMusic number: 99 loop: -1 play:)
				(if (< global159 3)
					(++ global159)
				else
					(= global159 0)
				)
				(if (== global199 2)
					(= state -1)
				)
				(= cycles 50)
			)
			(2
				(record setCycle: 0)
				(recordMusic stop:)
				(client setScript: 0)
			)
		)
	)
)
