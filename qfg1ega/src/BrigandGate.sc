;;; Sierra Script 1.0 - (do not remove this comment)
(script# 93)
(include game.sh)
(use Main)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
(use CastCalm)
(use CastOpen)
(use CastDazz)
(use Target)
(use LoadMany)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm93 0
	minotaur 1
	gate 2
	openGate 3
	bell 4
	archer1 5
	archer2 6
	archer3 7
	archer4 8
)

(local
	enteredViaCave
	local1
	local2
	minotaurDir
	local4
	spellCast
	local6
	local7
	seeDist =  5
	minotaurStunned
	local10
	minotaurMutter
)
(procedure (localproc_001c &tmp temp0)
	(= temp0 1)
	(switch minotaurDir
		(0
			(if (< (+ (minotaur x?) seeDist) (ego x?))
				(= temp0 0)
			)
		)
		(1
			(if (< (ego x?) (- (minotaur x?) seeDist))
				(= temp0 0)
			)
		)
		(2
			(if (< (+ (minotaur y?) seeDist) (ego y?))
				(= temp0 0)
			)
		)
		(else 
			(if (< (ego y?) (- (minotaur y?) seeDist))
				(= temp0 0)
			)
		)
	)
	(return temp0)
)

(procedure (localproc_00b6)
	(return
		(not
			(if
				(or
					local6
					local7
					minotaurStunned
					(ego inRect: 0 120 31 130)
					(ego inRect: 238 0 330 106)
					(ego inRect: 61 153 82 154)
					(and
						(== egoGait MOVE_SNEAK)
						(>= [egoStats STEALTH] 50)
						(localproc_001c)
					)
					(== (minotaur script?) minotaurLooks)
				)
			else
				(Btst fMinotaurDead)
			)
		)
	)
)

(instance rm93 of Room
	(properties
		picture 93
		style DISSOLVE
	)
	
	(method (init)
		(LoadMany VIEW
			vBrigandEntrance vEgoBreathHeavy vEgoRunning vEgoFall
			vEgoThrowing vEgoFallDown vMinotaurDefeated vEgoDefeated vEgoThrowingDagger
		)
		(Load SOUND 23)
		(cSound priority: 1 number: 23 loop: -1 play:)
		(super init:)
		(mouseDownHandler add: self)
		(self
			setFeatures: onBell onSign onDoor onBush onRock onFort
		)
		(StatusLine enable:)
		(NormalEgo)
		(HandsOff)
		(gateSign setPri: 6 init: addToPic:)
		(bell setPri: 6 init: stopUpd:)
		(bush init: stopUpd:)
		(if (Btst fBrigGateOpen)
			(gate setCel: 3 setPri: 6 ignoreActors: init: stopUpd:)
			(ego illegalBits: cWHITE)
		else
			(gate setCel: 0 setPri: 6 init: stopUpd:)
			(ego illegalBits: (| cWHITE cLRED))
		)
		(if (not (Btst fMinotaurDead))
			(= monsterNum vMinotaur)
			(= monsterHealth 186)
		)
		(switch prevRoomNum
			(89
				(= enteredViaCave TRUE)
				(if (not (Btst fMinotaurDead))
					(Load VIEW vMinotaur)
					(= local4 1)
					(= minotaurDir 0)
					(minotaur illegalBits: 0 init: setScript: patrol)
				)
				(ego posn: 2 123 init: setScript: (ScriptID 271 0))
			)
			(94
				(if (not (Btst fMinotaurDead))
					(Load VIEW vMinotaur)
					(= local4 0)
					(= minotaurDir 1)
					(minotaur
						setLoop: 1
						posn: 145 162
						illegalBits: 0
						init:
						setScript: patrol
					)
				)
				(if (Btst fBrigGateOpen)
					(ego posn: 174 98 init: setScript: (ScriptID 272 0))
				else
					(ego posn: 143 108 init: setScript: (ScriptID 275 0))
				)
			)
			(vMinotaur
				(Load VIEW vEgoDanceBow)
				(Load VIEW vMinotaurDefeated)
				(Bset fMinotaurDead)
				(minotaur
					view: vMinotaurDefeated
					setLoop: 0
					setCel: 0
					init:
					posn: 230 145
				)
				(ego posn: 235 158 init: setScript: (ScriptID 274 0))
			)
			(else 
				(if (not (Btst fMinotaurDead))
					(Load VIEW vMinotaur)
					(= local4 0)
					(= minotaurDir 1)
					(minotaur illegalBits: 0 init: setScript: patrol)
				)
				(ego posn: 235 188 init: setScript: (ScriptID 273 0))
			)
		)
	)
	
	(method (doit)
		(cond 
			((and local1 (== (ego edgeHit?) WEST))
				(Bset fBeenIn93)
				(= monsterNum (= monsterHealth 0))
				(curRoom newRoom: 89)
			)
			((and local1 (& (ego onControl: origin) cLRED))
				(Bset fBeenIn93)
				(= monsterNum (= monsterHealth 0))
				(curRoom newRoom: 94)
			)
			(
			(or (== (ego edgeHit?) SOUTH) (== (ego edgeHit?) EAST))
				(Bset fBeenIn93)
				(= monsterNum (= monsterHealth 0))
				(curRoom newRoom: 91)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(cond 
					((super handleEvent: event))
					((MouseClaimed ego event shiftDown)
						(HighPrint 93 0)
						;To the Minotaur, you look like a nice meal.
					)
				)
			)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'rest,nap')
						(EgoDead 93 1
							#title {Z-Z-Z-Z-Z-Z-Z-Z-Z-Z}
							#icon vIcons 0 0)
							;The Brigands oblige you by making your pleasant rest permanent!
						)
					((Said 'look>')
						(cond 
							((Said '[<at,around][/place,area]')
								(HighPrint 93 2)
								;Sheer cliff walls form a narrow box canyon where the brigands have built a fortress.
							)
							((Said '[<at]/boulder')
								(HighPrint 93 3)
								;The rocks look to be good hiding places.
							)
							((Said '[<at]/cliff')
								(HighPrint 93 4)
								;The rock walls look steep.
							)
							((Said '[<at]/log,gate')
								(HighPrint 93 5)
								;The gate appears to be fastened somehow on the inside.
							)
							((or (Said '<up') (Said '/sky'))
								(HighPrint 93 6)
								;The cliff seems very hard to climb.
							)
							((or (Said '<down') (Said '/ground,grass'))
								(HighPrint 93 7)
								;You can see marks in the ground, leading towards the bush.
							)
							((Said '/bush')
								(if enteredViaCave
									(HighPrint 93 8)
									;You see a bush that conceals the secret entrance.
								else
									(HighPrint 93 9)
									;The bush sits next to the cliff.  There are marks on the ground near the bush.
									)
								)
							((Said '/west')
								(if enteredViaCave
									(HighPrint 93 10)
									;You see that where the steep cliff walls meet the fortress, there is a pile of gravel.
									;Once away from the secret passage exit, the fortress is entirely obscured by rocks and brush.
								else
									(HighPrint 93 11)
									;The cliff to the west looks too sheer to climb.  There is a bush growing close to the cliff.
								)
							)
							((Said '/north')
								(HighPrint 93 12)
								;You see what must be the log walls and gate of the brigand fortress.
							)
							((Said '/east')
								(HighPrint 93 13)
								;You see where the sheer cliff walls meet the fortress wall with a pile of rocks.
							)
							((Said '/south')
								(HighPrint 93 14)
								;The walls of the canyon turn to the west.  You can see a large Minotaur patrolling the canyon.
							)
							((Said '/bell')
								(HighPrint 93 15)
								;A warning bell to alert all the brigands in the fortress.
							)
							((Said '/sign')
								(if (ego inRect: 150 92 250 140)
									(HighPrint 93 16)
									;The sign reads "Ring bell".
								else
									(HighPrint 93 17)
									;Step over and get a close look.
								)
							)
						)
					)
					((Said 'cast>')
						(= spellCast (SaidSpell event))
						(if (CastSpell spellCast)
							(switch spellCast
								(DETMAGIC
									(HighPrint 93 18)
									;There is no magical aura present.
								)
								(DAZZLE
									(cond 
										((or (Btst fMinotaurDead) local6 minotaurStunned local7)
											(HighPrint 93 19)
											;You waste a spell.
										)
										((CastDazz ego minotaurDazzled)
											(minotaur setScript: minotaurDazzled)
										)
									)
								)
								(FLAMEDART
									(cond 
										(
											(or
												(ego inRect: 47 141 90 163)
												(ego inRect: 0 119 35 126)
												(ego inRect: 238 0 330 106)
											)
											(HighPrint 93 20)
											;You'll have to give yourself room.
										)
										((Btst fMinotaurDead)
											(CastDart 0)
										)
										(else
											(if (< (ego x?) 160)
												(minotaur targDeltaX: -25)
											else
												(minotaur targDeltaX: 25)
											)
											(CastDart minotaur)
											(if (or local6 local7)
												(minotaur setScript: patrol)
											)
										)
									)
								)
								(CALM
									(cond 
										((or (Btst fMinotaurDead) local6)
											(HighPrint 93 19)
											;You waste a spell.
											)
										((CastCalm ego minotaurCalmed) (minotaur setScript: minotaurCalmed))
									)
								)
								(OPEN
									(if (or local2 (Btst fBrigGateOpen))
										(HighPrint 93 19)
										;You waste a spell.
									else
										(CastOpen ego openMess)
										(gate setScript: openMess)
									)
								)
								(else
									(HighPrint 93 19)
									;You waste a spell.
									)
							)
						)
					)
					((Said 'throw/dagger,dagger')
						(cond 
							(
								(or
									(ego inRect: 47 141 90 163)
									(ego inRect: 0 119 35 126)
									(ego inRect: 238 0 330 106)
								)
								(HighPrint 93 20)
								;You'll have to give yourself room.
							)
							((Btst fMinotaurDead) (ThrowKnife 0))
							(else
								(if (< (ego x?) 160)
									(minotaur targDeltaX: -25)
								else
									(minotaur targDeltaX: 25)
								)
								(ThrowKnife minotaur)
								(if (or local6 local7) (minotaur setScript: patrol))
							)
						)
					)
					((Said 'throw/boulder')
						(if
						(and (ThrowRock minotaur) (or local6 local7))
							(minotaur setScript: patrol)
						)
					)
					((Said 'get/boulder') (ego setScript: (ScriptID 103 0)))
					((Said 'climb')
						(cond 
							((ego inRect: 92 95 214 107)
								(HighPrint 93 21)
								;The logs of the wall are too slick, and there is no place to get a good hold on them.  You'll have to try somewhere else.
								)
							((ego inRect: 269 120 303 151)
								(HighPrint 93 22)
								;There is no good climbing place on the rock cliff to the east.
								)
							((and (< (ego x?) 97) (< (ego y?) 108)) (ego setScript: (ScriptID 277 0)))
							((ego inRect: 238 0 330 106)
								(if (TrySkill CLIMB tryClimbBrigandGate)
									(ego setScript: (ScriptID 276 0))
								else
									(HighPrint 93 23)
									;You don't have enough climbing skill to scale the wall.
								)
							)
							((ego inRect: 47 141 90 163)
								(HighPrint 93 24)
								;You can't climb the rock.
								)
							(else
								(HighPrint 93 25)
								;You're not in a good spot for climbing.
								)
						)
					)
					((Said 'open[/gate,door]')
						(cond 
							((Btst fBrigGateOpen)
								(HighPrint 93 26)
								;The gate is open.
								)
							((and local2 (ego inRect: 128 95 186 106)) (ego setScript: openGate))
							(local2
								(HighPrint 93 27)
								;You must get closer.
								)
							(else
								(HighPrint 93 28)
								;The gate appears to be fastened somehow on the inside.  You'll have to force the gate open.
								)
						)
					)
					((Said 'close,close[/gate,door]')
						(cond 
							((and (ego inRect: 128 95 186 106) (Btst fBrigGateOpen)) (ego setScript: closeGate))
							((Btst fBrigGateOpen)
								(HighPrint 93 27)
								;You must get closer.
								)
							(else
								(HighPrint 93 29)
								;The gate is closed.
								)
						)
					)
					((Said 'break,force,beat/gate,door')
						(cond 
							((Btst fBrigGateOpen)
								(HighPrint 93 30)
								;What for?
								)
							(
								(and
									(< 95 (ego x?))
									(< (ego x?) 230)
									(< (ego y?) 150)
								)
								(ego setScript: (ScriptID 278 0))
							)
							(else
								(HighPrint 93 27)
								;You must get closer.
								)
						)
					)
					((Said 'knock/door,gate')
						(HighPrint 93 31)
						;Why knock?  Use the bell.
						)
					((or (Said 'look/sign') (Said 'read/sign'))
						(if (ego inRect: 150 92 250 140)
							(HighPrint 93 16)
							;The sign reads "Ring bell".
						else
							(HighPrint 93 17)
							;Step over and get a close look.
						)
					)
					((Said 'ring/bell')
						(if (ego inRect: 150 92 250 140)
							(bell setScript: (ScriptID 279 0))
						else
							(HighPrint 93 32)
							;Unless you're Rubber Man you'll have to get closer.
						)
					)
				)
			)
		)
	)
	
	(method (notify param1)
		(switch param1
			(0 (= local1 1))
			(1 (= local1 0))
			(2 (= minotaurStunned 1))
			(3 (= minotaurStunned 0))
		)
	)
)

(instance gate of Prop
	(properties
		y 95
		x 129
		view vBrigandEntrance
	)
)

(instance bell of Prop
	(properties
		y 69
		x 197
		z 1
		view vBrigandEntrance
		loop 1
	)
)

(instance gateSign of Prop
	(properties
		y 57
		x 197
		z 1
		view vBrigandEntrance
		loop 2
	)
)

(instance bush of Prop
	(properties
		y 128
		x 23
		view vBrigandEntrance
		loop 3
	)
	
	(method (doit)
		(if
			(and
				(not (if (== egoGait MOVE_SNEAK) (>= [egoStats STEALTH] 50)))
				(ego inRect: 8 119 35 126)
				(== (bush script?) 0)
			)
			(bush setScript: jerkBush)
		)
		(super doit:)
	)
)

(instance archer1 of Actor
	(properties
		y 57
		x 247
		view vBrigandEntrance
		loop 4
		priority 5
	)
)

(instance archer2 of Actor
	(properties
		y 56
		x 207
		view vBrigandEntrance
		loop 4
		priority 5
	)
)

(instance archer3 of Actor
	(properties
		y 56
		x 109
		view vBrigandEntrance
		loop 5
		priority 5
	)
)

(instance archer4 of Actor
	(properties
		y 56
		x 69
		view vBrigandEntrance
		loop 5
		priority 5
	)
)

(instance lockSound of Sound
	(properties
		number 35
		priority 3
	)
)
;EO: The following messages seem very basic. Were these strings intended as placeholders?
(instance onBell of RFeature
	(properties
		nsTop 56
		nsLeft 193
		nsBottom 68
		nsRight 202
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onBell event shiftDown)
				(HighPrint 93 33)
				;Bell to alert gate guard.
				)
		)
	)
)

(instance onSign of RFeature
	(properties
		nsTop 48
		nsLeft 191
		nsBottom 55
		nsRight 201
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onSign event shiftDown)
				(HighPrint 93 34)
				;Sign reading "Ring Bell".
				)
		)
	)
)

(instance onDoor of RFeature
	(properties
		nsTop 38
		nsLeft 128
		nsBottom 95
		nsRight 186
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onDoor event shiftDown)
				(HighPrint 93 35)
				;Gate to fortress.  A very strong gate.
				)
		)
	)
)

(instance onBush of RFeature
	(properties
		nsTop 85
		nsLeft 9
		nsBottom 128
		nsRight 34
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onBush event shiftDown)
				(HighPrint 93 36)
				;Bush, a good place to hide.
				)
		)
	)
)

(instance onRock of RFeature
	(properties
		nsTop 125
		nsLeft 47
		nsBottom 159
		nsRight 94
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onRock event shiftDown)
				(HighPrint 93 37)
				;A good place to hide.
				)
		)
	)
)

(instance onFort of RFeature
	(properties
		nsBottom 24
		nsRight MAXRIGHT
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onFort event shiftDown)
				(HighPrint 93 38)
				;Brigands' fortress.
				)
		)
	)
)

(instance minotaur of TargActor
	(properties
		y 162
		x 60
		view vMinotaur
		targDeltaX 25
		targDeltaY -15
	)
	
	(method (doit)
		(if
			(and
				(localproc_00b6)
				(not (> (ego distanceTo: minotaur) 100))
			)
			(self setScript: minotaurLooks)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(cond 
					((super handleEvent: event))
					((not (MouseClaimed self event shiftDown)))
					((Btst fMinotaurDead)
						(HighPrint 93 39)
						;The Minotaur is out for the count.
						)
					(else
						(HighPrint 93 40)
						;The Minotaur seems to have very sensitive hearing.  He looks around every time the bush rustles.
						)
				)
			)
			(saidEvent
				(cond 
					((Said 'search[/bull,body,flail]')
						(cond 
							(local6 (HighPrint 93 41) (curRoom newRoom: vMinotaur))
							((Btst fMinotaurDead)
								(if local10
									(HighPrint 93 42)
									;As you attempt the search, the sleeping Minotaur awakes, and...
								else
									(= local10 1)
									(if
										(and
											(== curRoomNum daggerRoom)
											(or missedDaggers hitDaggers [invDropped iDagger])
										)
										(ego
											get: iDagger (+ missedDaggers hitDaggers [invDropped iDagger])
										)
										(HighPrint 93 43)
										;You retrieve your daggers.
									)
									(= [invDropped iDagger]
										(= hitDaggers
											(= missedDaggers (= daggerRoom 0))
										)
									)
									(HighPrint 93 44)
									;You quickly search the fallen Minotaur and find nothing.
									(HighPrint 93 45)
									;Wait a minute.
									(HighPrint 93 46)
									;The Minotaur's flail looks peculiar.  You pick it up and examine it closely.
									(HighPrint 93 47)
									;Cleverly concealed in the flail are 50 silvers.
									(HighPrint 93 48)
									;"A just reward for defeating such a valiant fighter," you tell yourself as you pocket the silvers.
									(ego get: iSilver 50)
								)
							)
							(else
								(HighPrint 93 49)
								;That's not a good idea.
								)
						)
					)
					((Said 'look/bull,monster,creature')
						(if (Btst fMinotaurDead)
							(HighPrint 93 50)
							;If you've seen one, you've seen them all.
						else
							(HighPrint 93 51)
							;Good ears but very poor peripheral vision.
						)
					)
					((Said 'kill,chop,beat/bull,monster,creature')
						(if (Btst fMinotaurDead)
							(HighPrint 93 52)
							;Why bother?  He's out for the count.
						else
							(HighPrint 93 53)
							;OK.  Here's your chance.
							(curRoom newRoom: vMinotaur)
						)
					)
				)
			)
		)
	)
	
	(method (getHurt damage)
		(cond 
			(
			(<= (= monsterHealth (- monsterHealth damage)) 0) (self setScript: minotaurDies) (Bset fMinotaurDead))
			((not local6) (rm93 setScript: delayLook))
		)
	)
)

(instance delayLook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(if (localproc_00b6)
					(minotaur setScript: minotaurLooks)
				)
			)
		)
	)
)

(instance minotaurLooks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(minotaur
					setLoop: 2
					setCel: 0
					setMotion: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(1
				(= minotaurDir 2)
				(if (localproc_001c)
					(minotaur setLoop: 3 setCel: 1)
					(= cycles 1)
				else
					(self changeState: 3)
				)
			)
			(2
				(minotaur setCel: 2)
				(= minotaurDir 3)
				(= cycles 1)
			)
			(3
				(if
					(or
						(ego inRect: 0 120 31 130)
						(ego inRect: 238 0 330 106)
						(ego inRect: 61 153 82 154)
					)
					(if (< 5 minotaurMutter)
						(= minotaurMutter 0)
					else
						(++ minotaurMutter)
					)
					(switch minotaurMutter
						(0
							(HighPrint 93 54)
							;"I've been out here too long.  My imagination is taking over."
						)
						(1
							(HighPrint 93 55)
							;"What was that noise?"
						)
						(2
							(HighPrint 93 56)
							;"Must be those Brigands playing games."
						)
						(3
							(HighPrint 93 57)
							;"I guess I'm just getting jumpy."
						)
						(4
							(HighPrint 93 58)
							;"Is anyone there?"
						)
						(5
							(HighPrint 93 59)
							;"This place is spooky."
						)
					)
					(client setScript: patrol)
				else
					(curRoom newRoom: vMinotaur)
				)
			)
		)
	)
)

(instance jerkBush of Script
	(properties)
	
	(method (doit)
		(if (and (== egoGait MOVE_SNEAK) (>= [egoStats STEALTH] 50))
			(self dispose:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (bush setCycle: EndLoop self))
			(1
				(if (localproc_00b6)
					(minotaur setScript: minotaurLooks)
				)
				(= seconds 17)
			)
			(2 (self dispose:))
		)
	)
)

(instance openGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(gate
					ignoreActors:
					cycleSpeed: 3
					setPri: 4
					setCycle: EndLoop self
				)
			)
			(1
				(Bset fBrigGateOpen)
				(ego illegalBits: cWHITE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance closeGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(gate
					ignoreActors: 1
					setCel: 3
					cycleSpeed: 3
					setCycle: BegLoop self
				)
			)
			(1
				(Bclr fBrigGateOpen)
				(ego illegalBits: (| cWHITE cLRED))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance openMess of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= minotaurStunned 1)
				(HighPrint 93 60)
				;You hear a snick as the hasp on the gate is opened.
				(lockSound number: (SoundFX 35) init: play:)
				(= local2 1)
				(= cycles 1)
			)
			(2
				(lockSound dispose:)
				(= seconds 3)
			)
			(3
				(= minotaurStunned 0)
				(self dispose:)
			)
		)
	)
)

(instance patrol of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(minotaur moveSpeed: 1 cycleSpeed: 1 setCycle: 0)
				(if local6
					(self changeState: 5)
				else
					(= minotaurStunned 0)
					(switch minotaurDir
						(0 (self changeState: 10))
						(1 (self changeState: 14))
						(2
							(if local4
								(self changeState: 17)
							else
								(self changeState: 13)
							)
						)
						(3
							(if local4
								(self changeState: 3)
							else
								(self changeState: 1)
							)
						)
					)
				)
			)
			(1
				(= local4 0)
				(= minotaurDir 1)
				(minotaur view: vMinotaur setLoop: 3 setCel: 0)
				(= cycles 1)
			)
			(2 (self changeState: 14))
			(3
				(= local4 1)
				(= minotaurDir 0)
				(minotaur view: vMinotaur setLoop: 3 setCel: 1)
				(= cycles 1)
			)
			(4 (self changeState: 10))
			(5
				(minotaur cycleSpeed: 4 setCycle: BegLoop self)
			)
			(6
				(= local6 0)
				(= minotaurDir 2)
				(self changeState: 0)
			)
			(10
				(= local4 1)
				(= minotaurDir 0)
				(minotaur
					view: vMinotaur
					setLoop: 0
					setCel: 0
					setCycle: Forward
					setMotion: MoveTo 279 (minotaur y?) self
				)
			)
			(11
				(= local4 0)
				(minotaur setLoop: 2 setCel: 3)
				(= cycles 1)
			)
			(12
				(minotaur setLoop: 2 setCel: 2)
				(= cycles 1)
			)
			(13
				(= minotaurDir 1)
				(minotaur view: vMinotaur setLoop: 2 setCel: 1)
				(= cycles 1)
			)
			(14
				(= local4 0)
				(= minotaurDir 1)
				(minotaur
					view: vMinotaur
					setLoop: 1
					setCel: 0
					setCycle: Forward
					setMotion: MoveTo 60 (minotaur y?) self
				)
			)
			(15
				(= local4 1)
				(minotaur setLoop: 2 setCel: 1)
				(= cycles 1)
			)
			(16
				(minotaur setLoop: 2 setCel: 2)
				(= cycles 1)
			)
			(17
				(= minotaurDir 0)
				(minotaur view: vMinotaur setLoop: 2 setCel: 3)
				(= cycles 1)
			)
			(18 (self changeState: 10))
		)
	)
)

(instance minotaurCalmed of Script
	(method (doit)
		(if (and (not (ego script?)) (not isHandsOff))
			(HandsOff)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= minotaurStunned 1)
				(HandsOff)
				(minotaur setCycle: 0 setMotion: 0)
			)
			(1
				(switch minotaurDir
					(0 (self changeState: 3))
					(1 (self changeState: 8))
					(2 (self changeState: 10))
					(3 (self changeState: 2))
				)
			)
			(2
				(minotaur setLoop: 0 setCel: 0)
				(= cycles 1)
			)
			(3
				(minotaur setLoop: 2 setCel: 3)
				(= cycles 1)
			)
			(4
				(minotaur setLoop: 2 setCel: 2)
				(= cycles 1)
			)
			(5 (self changeState: 10))
			(8
				(= local4 1)
				(minotaur setLoop: 2 setCel: 1)
				(= cycles 1)
			)
			(9
				(minotaur setLoop: 2 setCel: 2)
				(= cycles 1)
			)
			(10
				(minotaur
					setLoop: 4
					setCel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(11
				(client setScript: minotaurSleeps)
			)
		)
	)
)

(instance minotaurDazzled of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= minotaurStunned 1)
				(minotaur setCycle: 0 setMotion: 0)
			)
			(1
				(minotaur setLoop: 4 setCel: 1)
				(= cycles 5)
			)
			(2
				(minotaur setCel: 0)
				(= cycles 5)
			)
			(3
				(minotaur setCel: 1)
				(= cycles 2)
			)
			(4
				(minotaur setCel: 0)
				(= cycles 2)
			)
			(5
				(minotaur setCel: 1)
				(= local7 1)
				(HandsOn)
				(= seconds 12)
			)
			(6
				(= local7 0)
				(= minotaurStunned 0)
				(client setScript: patrol)
			)
		)
	)
)

(instance minotaurSleeps of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (< (minotaur x?) 79)
					(minotaur
						setLoop: 0
						cycleSpeed: 1
						moveSpeed: 1
						setCycle: Walk
						setMotion: MoveTo 79 162 self
					)
				else
					(minotaur
						setLoop: 1
						cycleSpeed: 1
						moveSpeed: 1
						setCycle: Walk
						setMotion: MoveTo 79 162 self
					)
				)
			)
			(1
				(= local6 1)
				(minotaur
					view: vMinotaurDefeated
					setLoop: 0
					cel: 0
					cycleSpeed: 4
					illegalBits: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(2
				(= minotaurStunned FALSE)
				(minotaur stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance minotaurDies of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(minotaur
					view: vMinotaurDefeated
					setLoop: 0
					cel: 0
					setMotion: 0
					cycleSpeed: 4
					illegalBits: 0
					setCycle: EndLoop self
				)
			)
			(1
				(minotaur stopUpd:)
				(self dispose:)
			)
		)
	)
)
