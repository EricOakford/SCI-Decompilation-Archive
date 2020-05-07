;;; Sierra Script 1.0 - (do not remove this comment)
(script# 83)
(include game.sh)
(use Main)
(use Sleep)
(use Rest)
(use TalkObj)
(use LoadMany)
(use RFeature)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm83 0
	hermitTalk 1
	dummy 2
)

(local
	sittingOnChair
	chatHermit
)
(instance pfSnd of Sound
	(properties
		priority 8
	)
)

(instance poof of Prop
	(properties
		view vHenryInside
		loop 5
	)
)

(instance scroll of Prop
	(properties
		y 162
		x 194
		view vHenryInside
	)
)

(instance hermitWin of SysWindow
	(properties
		color 8
		title {Hermit:}
	)
	
	(method (open &tmp temp0)
		(= top (- top (= temp0 (- top 12))))
		(= bottom (- bottom temp0))
		(super open:)
	)
)

(instance hermitTalk of TalkObj
	(properties
		tLoop 3
		cSpeed 1
	)
)

(instance bed of RPicView
	(properties
		y 164
		x 67
		view vHenryInside
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed self event shiftDown)
					(Said 'look/bed,hay')
				)
				(HighPrint 83 0)
				;The straw is relatively fresh and looks more comfortable to lie upon than the rocks.
			)
		)
	)
)

(instance table of RPicView
	(properties
		y 180
		x 189
		view vHenryInside
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed self event shiftDown)
					(Said 'look/table')
				)
				(HighPrint 83 1)
				;Yes, what you see is a table.
			)
		)
	)
)

(instance chair of RPicView
	(properties
		y 167
		x 159
		view vHenryInside
		loop 1
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed self event shiftDown)
					(Said 'look/chair')
				)
				(HighPrint 83 2)
				;You see a chair.
			)
		)
	)
)

(instance cot of RPicView
	(properties
		y 168
		x 227
		view vHenryInside
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
			(or (MouseClaimed self event shiftDown)
				(Said 'look/cot')) 
				(HighPrint 83 3)
				;You see a cot.
				)
		)
	)
)

(instance candle of Prop
	(properties
		y 155
		x 185
		view vHenryInside
		loop 4
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed self event shiftDown)
					(Said 'look/candle,flame')
				)
				(HighPrint 83 4)
				;A single candle sits upon the table.
			)
		)
	)
)

(instance hermit of Prop
	(properties
		y 162
		x 227
		view vHenryInside
		loop 3
	)
	
	(method (doit)
		(hermitTalk doit:)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed self event shiftDown)
					(Said 'look/ermit,man')
				)
				(HighPrint 83 5)
				;You see the shaggiest little man (you think it's a man, anyway) you've ever seen.
			)
			(else (hermitTalk handleEvent: event))
		)
	)
)

(instance rm83 of Room
	(properties
		picture 83
		style WIPELEFT
	)
	
	(method (init)
		(super init:)
		(StatusLine enable:)
		(NormalEgo)
		(Load SOUND 41)
		(LoadMany SCRIPT 143 144 145)
		(SolvePuzzle POINTS_MEETHERMIT 5)
		(ego loop: 0 posn: 143 185 init:)
		(bed priority: 0)
		(addToPics
			add: bed table chair cot
			eachElementDo: #init
			doit:
		)
		(features add: bed table chair cot)
		(candle init: setPri: 14 cycleSpeed: 1 setCycle: Forward)
		(hermit init:)
		(hermitTalk tWindow: hermitWin actor: hermit init:)
		(cSound stop: number: 41 loop: -1 play:)
		(keyDownHandler add: hermit)
		(pfSnd number: (SoundFX 28))
		(self setScript: (ScriptID 143 0))
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((or sittingOnChair isHandsOff) (if (User canControl:) (User canControl: FALSE)))
			((not (User canControl:)) (User canControl: TRUE))
		)
		(if
			(and
				(== (ego edgeHit?) SOUTH)
				(!= (self script?) sLeaving)
			)
			(if (not (Btst VISITED_HENRYINSIDE))
				(self setScript: sLeaving)
			else
				(curRoom newRoom: 82)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look[<around,at][/room]')
						(HighPrint 83 6)
						;The cave is cold and clammy and it smells like mildew and wet dog hair in here!
						)
					((Said 'look>')
						(cond 
							((Said '/cave,wall')
								(HighPrint 83 7)
								;The walls are covered with phosphorescent fungus.
								)
							((Said '/fungus')
								(HighPrint 83 8)
								;The phosphorescent fungus glows eerily in the cave's dim light.
								)
							((Said '/scroll,spell')
								(if (cast contains: scroll)
									(HighPrint 83 9)
									;It's on the table.
								else
									(HighPrint 83 10)
									;There's nothing like that here.
								)
							)
						)
					)
					((Said 'read/scroll,spell')
						(if (cast contains: scroll)
							(HighPrint 83 9)
							;It's on the table.
						else
							(HighPrint 83 10)
							;There's nothing like that here.
						)
					)
					((Said 'ask>')
						(= chatHermit TRUE)
						(cond 
							((Said '//fall,water') (self setScript: (ScriptID 145 8)))
							((Said '//magic,ladder') (self setScript: (ScriptID 145 7)))
							(
							(or (Said '//trigger') (Said '//spell<trigger')) (self setScript: (ScriptID 145 5)))
							((Said '//spell') (self setScript: (ScriptID 145 6)))
							((Said '//wizard,erasmus') (self setScript: (ScriptID 144 0)))
							((Said '//familiar,fenrus') (self setScript: (ScriptID 144 1)))
							((Said '//game') (self setScript: (ScriptID 144 2)))
							((Said '//bandit') (self setScript: (ScriptID 144 3)))
							((Said '//warlock') (self setScript: (ScriptID 144 4)))
							((Said '//mirror') (self setScript: (ScriptID 144 5)))
							((Said '//cave,home') (self setScript: (ScriptID 145 0)))
							((Said '//ermit') (self setScript: (ScriptID 145 1)))
							((Said '//family,hortense,arry,mama,dad,farther') (self setScript: (ScriptID 145 2)))
							((Said '//enry,name') (self setScript: (ScriptID 145 3)))
							((Said '//cribbage') (self setScript: (ScriptID 145 4)))
							((Said '//nap,bed,hay') (self setScript: sSleep))
							((Said '//scroll')
								(if (Btst ASKED_TRIGGER_SCROLL)
									(if (cast contains: scroll)
										(HighPrint 83 9)
										;It's on the table.
									else
										(self setScript: sNixScroll)
									)
								else
									(self setScript: sAskScroll)
								)
							)
							(
							(Said '//candle,table,chair,cot,flame,door,hay,food') (self setScript: sMisc))
							(else (= chatHermit FALSE) (event claimed: 1) (self setScript: sElse))
						)
						(if chatHermit (SolvePuzzle POINTS_TALKTOHERMIT 2))
					)
					((Said 'buy/') (self setScript: sMisc))
					((Said 'chat/') (self setScript: (ScriptID 145 3)))
					((Said 'gave,gave/ration')
						(cond 
							((not (ego use: iRations 1))
								(HighPrint 83 11)
								;You can't share what you don't have.
								)
							(Night
								(Say hermitTalk 83 12)
								;"Me guest bed is the straw in the corner over there.
								;You're welcome to stay the night since you've shared your rations.  When you want to sleep, just say so."
								(Bset GAVE_HERMIT_RATION)
								)
							(else
								(Say hermitTalk 83 13)
								;"Me guest bed is the straw in the corner over there.
								;You're welcome to come back and sleep tonight since you've shared your rations."
								(Bset GAVE_HERMIT_RATION)
								)
						)
					)
					(
						(or
							(Said 'nap/')
							(Said 'go/bed,nap')
							(Said 'get<on/bed,hay')
						)
						(cond 
							(sittingOnChair
								(HighPrint 83 14)
								;You can't sleep in this old rickety chair.  Get up and sleep on the bed of straw.
								)
							((> (ego x?) 165)
								(HighPrint 83 15)
								;Get closer to the bed of straw.
								)
							((not (if (Btst GAVE_HERMIT_RATION) else (>= [invNum iRations] 1))) (self setScript: sNoSleep))
							((not (NeedSleep)) (EgoSleeps 5))
							(else
								(if (not (Btst GAVE_HERMIT_RATION)) (ego use: iRations 1))
								(Bclr GAVE_HERMIT_RATION)
								(self setScript: sGoSleep)
							)
						)
					)
					((Said 'sat/')
						(if (not sittingOnChair)
							(if (ego inRect: 140 150 174 174)
								(ego
									view: vEgoSittingHenry
									setLoop: 8
									ignoreActors: 1
									illegalBits: 0
									posn: 159 167
									stopUpd:
								)
								(= sittingOnChair TRUE)
							else
								(HighPrint 83 16)
								;You need to get closer to the chair.
							)
						else
							(HighPrint 83 17)
							;You are already sitting... on your brain, perhaps?
						)
					)
					((or (Said 'stand,get<up/') (Said 'stand/'))
						(if sittingOnChair
							(NormalEgo)
							(= sittingOnChair FALSE)
							(ego posn: 156 171 loop: 0)
						else
							(HighPrint 83 18)
							;You're not sitting.
						)
					)
					((Said 'put,extinguish/candle,flame')
						(HighPrint 83 19)
						;The 'ermit wouldn't like that.
						)
					((Said 'search/')
						(HighPrint 83 20)
						;You should wait until the hermit is asleep to check his place out.
						)
					((Said 'play/cribbage,card,game,cardgame')
						(cond 
							((== Day dayLastPlayedCribbage)
								(HighPrint 83 21)
								;Despite your intentions, you can't bring yourself to spend any more time playing with the talkative 'Enry.
								)
							(sittingOnChair
								(= dayLastPlayedCribbage Day)
								(EgoRests 60 0)
								(HighPrint 83 22)
								;You spend an hour at the game while 'Enry talks.  You've obviously made his day.
							)
							(else
								(HighPrint 83 23)
								;You should sit down first.
								)
						)
					)
					((Said 'get/scroll')
						(if (cast contains: scroll)
							(scroll dispose:)
							(ego get: iPaper)
							(HighPrint 83 24)
							;The scroll vanishes even as you read the magical runes upon it.
							;You now have the knowledge to cast a "Trigger" spell.
							(Bset LEARNED_TRIGGER)
							(ego learn: TRIGGER 10)
							(SolvePuzzle POINTS_LEARNTRIGGER 4 MAGE)
						else
							(HighPrint 83 25)
							;You see no scroll.
						)
					)
					(
					(or (Said 'cast/detect') (Said 'cast/spell<detect'))
					(if (CastSpell DETMAGIC)
						(HighPrint 83 26)
						;You can detect a faint aura of magic here.
						)
					)
					(
					(or (Said 'cast/open') (Said 'cast/spell<open')) (if (CastSpell OPEN)
						(HighPrint 83 27)
						;There is no need to use magic to open anything here.
						)
					)
					((Said 'get,grab/candle,cot,hay,bed,table,chair')
						(HighPrint 83 28)
						;It's not polite to steal from your 'airy little 'ost.
						(HighPrint 83 29)
						;You seem to have made the 'ermit un'appy.
						(Bset HENRY_DEADLY_TP)
						(self setScript: TPego)
					)
					((Said 'kill,attack,throw,beat,cast,fight/')
						(HighPrint 83 29)
						;You seem to have made the 'ermit un'appy.
						(Bset HENRY_DEADLY_TP) (self setScript: TPego))
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance sSleep of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hermitTalk caller: self)
				(HandsOff)
				(Say hermitTalk 83 30)
				;"Me guest bed is the straw in the corner over there. You're welcome to stay if you share some rations and play some cribbage."
			)
			(1 (HandsOn))
		)
	)
)

(instance sAskScroll of Script
	(properties)
	
	(method (changeState newState)
		(if client
			(switch (= state newState)
				(0
					(Bset ASKED_TRIGGER_SCROLL)
					(hermitTalk caller: self)
					(HandsOff)
					(if [egoStats MAGIC]
						(Say hermitTalk 83 31)
						;"If you be wanting to learn the spell, I'll be pleased to share it to you.
						;It's just getting molding in me 'iding place."
					else
						(Say hermitTalk 83 32)
						;"I think you be 'avin too little magic for it to be any use to you."
						(HandsOn)
						(client setScript: 0)
					)
				)
				(1
					(Say hermitTalk 83 33)
					;"Do you want it?"
				)
				(2
					(User canInput: TRUE)
					(= seconds 6)
				)
				(3
					(client setScript: sNoScroll)
					(HandsOn)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (and (== (event type?) saidEvent) (== state 2))
			(= seconds 0)
			(if (Said 'affirmative,please')
				(client setScript: sGetScroll)
			else
				(event claimed: TRUE)
				(client setScript: sNoScroll)
			)
		)
	)
)

(instance sNoScroll of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hermitTalk caller: self)
				(HandsOff)
				(Say hermitTalk 83 34)
				;"Oh well, maybe sumone else can use it sumtime."
			)
			(1 (HandsOn))
		)
	)
)

(instance sNixScroll of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hermitTalk caller: self)
				(HandsOff)
				(if (Btst LEARNED_TRIGGER)
					(Say hermitTalk 83 35)
					;"You already 'ave me only scroll."
				else
					(Say hermitTalk 83 36)
					;"Yes, I still have me magic scroll, but you didn't seem to want it. 'Ave you changed your mind?"
				)
			)
			(1
				(User canInput: TRUE)
				(= seconds 6)
			)
			(2
				(client setScript: sNoScroll)
				(HandsOn)
			)
		)
	)
	
	(method (handleEvent event)
		(if (and (== (event type?) saidEvent) (== state 1))
			(= seconds 0)
			(if (Said 'affirmative,please')
				(client setScript: sGetScroll)
			else
				(event claimed: TRUE)
				(client setScript: sNoScroll)
			)
		)
	)
)

(instance sGetScroll of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(hermitTalk caller: self)
				(Say hermitTalk 83 37)
				;"Let's see.  'Ow does Erasmus do it?  Oh, yes."
			)
			(1
				(Say hermitTalk 83 38)
				;" 'E summons it with a Trigger.  'Ere goes!"
			)
			(2
				(scroll
					loop: 7
					setPri: 14
					init:
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(pfSnd init: play:)
			)
			(3
				(scroll setLoop: 2 setCel: 1)
				(Say hermitTalk 83 39)
				;"There you 'as it."
			)
			(4 (HandsOn))
		)
	)
)

(instance sMisc of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hermitTalk caller: self)
				(HandsOff)
				(Say hermitTalk 83 40)
				;"I don't 'ave much, but wot I 'ave, I 'ave, an' t'isn't for sale."
			)
			(1 (HandsOn))
		)
	)
)

(instance sElse of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hermitTalk caller: self)
				(HandsOff)
				(Say hermitTalk 83 41)
				;"Well, see'en as I don't get out much, I don't know much."
			)
			(1 (HandsOn))
		)
	)
)

(instance sLeaving of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset VISITED_HENRYINSIDE)
				(hermitTalk caller: self)
				(HandsOff)
				(Say hermitTalk 83 42)
				;"If you be ever needen a place to stay the night and you're in the area,
				;I could be willen to put you up for the night for sum rations and a couple o' games o' cribbage."
			)
			(1 (curRoom newRoom: 82))
		)
	)
)

(instance TPego of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset VISITED_HENRYINSIDE)
				(HandsOff)
				(pfSnd init: play:)
				(poof
					posn: (ego x?) (ego y?)
					setCycle: CycleTo 6 1 self
					cycleSpeed: 4
					init:
				)
			)
			(1
				(ego dispose:)
				(poof setCycle: EndLoop self)
			)
			(2 (= cycles 10))
			(3
				(poof dispose:)
				(curRoom newRoom: 82)
			)
		)
	)
)

(instance sGoSleep of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(HighPrint 83 43)
				;After having traded a ration, which the 'ermit promptly swallowed,
				;and spending a rather boring hour of cribbage and listening to 'Enry chatter, you hit the hay.
				(ego illegalBits: 0 setMotion: MoveTo 74 150 self)
			)
			(1
				(ego view: vEgoThrowing setLoop: 1 setCycle: EndLoop self)
			)
			(2
				(ego
					view: vEgoFall
					setLoop: 0
					setCel: 2
					posn: 72 168
					setPri: 13
					stopUpd:
				)
				(hermit setLoop: 3 setCycle: Forward)
				(= seconds 6)
			)
			(3
				(HighPrint 83 44)
				;Unfortunately, since the fungus on the walls shines at night so the room doesn't get dark,
				;and the fact the hermit talks most of the night, you only get five hours rest.
				(EgoSleeps 5)
				(Bset VISITED_HENRYINSIDE)
				(curRoom newRoom: 82)
			)
		)
	)
)

(instance sNoSleep of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(hermitTalk caller: self)
				(Say hermitTalk 83 45)
				;"You can't sleep here, you got's no rations to give me."
			)
			(1
				(Bset HENRY_SAFE_TP)
				(client setScript: TPego)
			)
		)
	)
)

(instance dummy of Script
	(properties)
)
