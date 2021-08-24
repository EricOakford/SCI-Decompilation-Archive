;;; Sierra Script 1.0 - (do not remove this comment)
(script# 14)
(include game.sh)
(use Main)
(use ThrowKnife)
(use CastCalm)
(use CastDazz)
(use Target)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm14 0
	bear 1
	bearUp 2
)
(enum
	bearNEUTRAL
	bearUPRIGHT
	bearDOCILE
	bearTHREATEN
	bearDEAD
	bearFREE
)

(local
	bearState
	local1
	local2
	bearKillsEgo
	distFromBear
	bearCel =  6
	burnedBear
	hurtBear
	dazzledBear
	calmedBear
	local10
	local11
	local12
	dartX
	dartY
	local15
	local16
	local17 = [253 295 149 226 284 319 93 152 223 29 65]
	local28 = [101 64 19 164 152 103 91 61 58 130 54]
	dripX = [144 249 283 88 165]
	dripY = [184 221 219 201 168]
	dripIndex
	local50 = [146 124 319 189]
)
(procedure (localproc_06a8)
	(if (< (ego x?) (bear x?))
		(= local1 110)
		(= local2 40)
	else
		(= local1 20)
		(= local2 12)
	)
)

(procedure (localproc_0e88)
	(= local11 (- (bear x?) (ego x?)))
	(= local12 (- (bear y?) (ego y?)))
	(= dartX (+ (ego x?) (* local11 30)))
	(= dartY (+ (ego y?) (* local12 30)))
)

(instance magicHit of Sound
	(properties
		number 45
		priority 1
	)
)

(instance egoShoots of Sound
	(properties
		number 33
		priority 2
	)
)

(instance rm14 of Room
	(properties
		picture 14
		style WIPERIGHT
		east 15
		south 13
	)
	
	(method (init)
		(if (not (Btst fBearGone))
			(LoadMany VIEW vBear vEgoDefeatedMagic vKoboldCave vEgoThrowing)
			(= monsterHealth MAX_HP_BEAR)
			(= monsterNum vBear)
		)
		(Load SOUND 20)
		(super init:)
		(if (== egoGait MOVE_RUN)
			(ChangeGait MOVE_WALK FALSE)
		)
		(if (or (!= prevRoomNum 15) (== (cSound state?) 0))
			(cSound priority: 1 number: 20 loop: -1 play:)
		)
		(if (not (Btst fBearGone))
			(if (ego knows: FLAMEDART)
				(egoShoots number: (SoundFX 33) init:)
				(magicHit number: (SoundFX 45) init:)
			)
			(dart init: stopUpd:)
			(puff init: stopUpd:)
			(bear ignoreActors: init: setPri: 11 stopUpd:)
		)
		(drip init: setScript: dripScript)
		(= dripIndex (Random 0 4))
		(StatusLine enable:)
		(NormalEgo)
		(ego init:)
		(if (not (Btst fBearGone))
			(ego illegalBits: (| cWHITE cYELLOW))
		)
		(switch prevRoomNum
			(15
				(ego posn: 318 145 setMotion: MoveTo 308 151)
				(= local1 20)
				(= local2 12)
			)
			(else 
				(ego setScript: entranceMsg)
				(= local1 110)
				(= local2 40)
			)
		)
	)
	
	(method (dispose)
		(Bset fBeenIn14)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell)
		(switch (event type?)
			(saidEvent
				(cls)
				(cond 
					((Said 'look>')
						(cond 
							((Said '/stalactite')
								(HighPrint 14 0)
								;They're like stalagmites, but they go the other way.
							)
							((Said '/stalagmite')
								(HighPrint 14 1)
								;They're like stalactites, but they go the other way.
							)
							((Said '[<at,around][/!*,cave,room]')
								(HighPrint 14 2)
								;The cavern contains some impressive formations and is rather beautiful, as caves go.
							)
							((Said '/wall,fungus,north,west,formation')
								(HighPrint 14 3)
								;The stalactites, stalagmites, and cave walls glow from a phosphorescent fungus growing there.
							)
							((or (Said '<up') (Said '/ceiling'))
								(HighPrint 14 4)
								;The stalactites grow slowly.
								(HighPrint 14 5)
								;...or are they stalagmites?
							)
							((or (Said '<down') (Said '/floor'))
								(HighPrint 14 6)
								;The stalagmites grow slowly.
								(HighPrint 14 7)
								;...or are they stalactites?
							)
							((Said '/south,entrance,open') 
								(HighPrint 14 8)
								;The light from outside illuminates the cave opening.
							)
							((Said '/east')
								(HighPrint 14 9)
								;Beyond the bear, the cave seems to continue.  The bear blocks the way.
							)
							((Said '/baron')
								(HighPrint 14 10)
								;The baron's in his castle.
							)
							((Said '/barnard,man')
								(if (Btst fBearGone)
									(HighPrint 14 11)
									;There is no sign of the Baronet or the bear.
								else
									(HighPrint 14 12)
									;You don't see any people -- just that bear.
								)
							)
							((Said '/bear,animal,creature,monster')
								(cond 
									((Btst fBearGone)
										(HighPrint 14 11)
										;There is no sign of the Baronet or the bear.
									)
									((or calmedBear (Btst fBearFriendly))
										(HighPrint 14 13)
										;The bear appears docile for the time being.
									)
									(dazzledBear
										(HighPrint 14 14)
										;Stunned, the bear is temporarily frozen.
									)
									(burnedBear
										(HighPrint 14 15)
										;Although looking somewhat scorched, the bear is still very dangerous.
									)
									((or (== bearState bearNEUTRAL) (== bearState bearDOCILE))
										(HighPrint 14 16)
										;On one side of this cavern is a creature which looks like a large bear.
									)
									(else
										(HighPrint 14 17)
										;The bear looks menacing and angry.  There is something attached to its leg.
									)
								)
							)
							((Said '/leg,chain,manacle,feet,hasp')
								(= distFromBear (ego distanceTo: bear))
								(cond 
									((Btst fBearGone)
										(HighPrint 14 18)
										;There is no sign of the bear or his chains.
									)
									((>= distFromBear local1)
										(HighPrint 14 19)
										;You can't see very well.
										)
									(else (HighPrint 14 20)
										;There is a manacle and chain attached to the bear's leg.
										)
								)
							)
							(else (HighPrint 14 21)
								;I just don't know what to tell you.
								)
						)
					)
					((Said 'get>')
						(cond 
							((Said '/fungus')
								(HighPrint 14 22)
								;The fungus is slimy and stuck tight to the cave walls.
							)
							((Said '/bear,stalactite,stalagmite')
								(HighPrint 14 23)
								;You're kidding, right?
							)
							((and (not (Btst fBearGone)) (Said '/dagger,dagger'))
								(HighPrint 14 24)
								;Better not.  You might make the bear angry.
							)
						)
					)
					((Said 'throw/dagger,dagger')
						(++ hurtBear)
						(ThrowKnife (if (Btst fBearGone) 0 else bear))
						(Bclr fBearFriendly)
						(= calmedBear FALSE)
					)
					((Said 'throw/boulder')
						(HighPrint 14 25)
						;That won't help you.
					)
					((Said 'climb')
						(HighPrint 14 26)
						;The walls are too slimy to climb.
					)
					((Said 'cast>')
						(= spell (SaidSpell event))
						(if (CastSpell spell)
							(switch spell
								(DETMAGIC
									(if (Btst fBearGone)
										(HighPrint 14 27)
										;There is no magic in the cave.
									else
										(HighPrint 14 28)
										;There is an aura of magic throughout the cavern.  It seems to center on the bear.
									)
								)
								(DAZZLE
									(cond 
										((Btst fBearGone)
											(HighPrint 14 29)
											;There is no point to that.
										)
										((CastDazz)
											(= dazzledBear TRUE)
										)
									)
								)
								(FLAMEDART
									(if (Btst fBearGone)
										(HighPrint 14 29)
										;There is no point to that.
									else
										(Bclr fBearFriendly)
										(= calmedBear FALSE)
										(dart setScript: flameDart)
									)
								)
								(CALM
									(cond 
										((Btst fBearGone)
											(HighPrint 14 29)
											;There is no point to that.
											)
										((CastCalm)
											(= calmedBear TRUE)
											(SolvePuzzle f14CalmBear 5)
										)
									)
								)
								(OPEN
									(if (Btst fBearGone)
										(HighPrint 14 29)
										;There is no point to that.
									else
										(HighPrint 14 30)
										;The magic about the bear's manacle is too great for your Open spell.
									)
								)
								(else
									(event claimed: FALSE)
								)
							)
						)
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance dart of Actor
	(properties
		z 25
		view vEgoMagicFlameDart
		illegalBits $0000
	)
)

(instance puff of Prop
	(properties
		z 25
		view vEgoMagicFlameDart
		loop 3
	)
)

(instance bear of TargActor
	(properties
		y 144
		x 273
		view vBear
		targDeltaY -5
	)
	
	(method (doit)
		(= distFromBear (ego distanceTo: self))
		(if (and hurtBear (== prevRoomNum 15))
			(localproc_06a8)
		)
		(cond 
			((or bearKillsEgo (== bearState bearDEAD) (== bearState bearFREE)))
			(dazzledBear
				(if (== (-- dazzledBear) 0)
					(bear startUpd:)
				else
					(localproc_06a8)
				)
			)
			((or (Btst fBearFriendly) calmedBear)
				(localproc_06a8)
				(if (or (== bearState bearTHREATEN) (== bearState bearUPRIGHT))
					(bear setScript: bearDrop)
				)
			)
			((> distFromBear local1)
				(if (not hurtBear)
					(switch bearState
						(bearUPRIGHT
							(bear setScript: bearDrop)
						)
						(bearTHREATEN
							(bear setScript: bearDrop)
						)
					)
				)
			)
			((and (>= local1 distFromBear) (>= distFromBear local2))
				(if (and (not hurtBear) (== bearState bearNEUTRAL))
					(bear setScript: bearUp)
				)
			)
			((> (+ (ego y?) 15) (bear y?))
				(cond 
					((< (ego x?) (- (bear x?) 15))
						(if (not bearKillsEgo)
							(++ bearKillsEgo)
							(bear setScript: bearKills)
						)
					)
					((not bearKillsEgo)
						(++ bearKillsEgo)
						(bear setScript: bearKillsRm15)
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event &tmp temp0)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'pat/bear,animal')
						(HighPrint 14 31)
						;You'd better not.  The bear isn't wearing a flea collar.
						)
					((Said 'chat/bear,animal')
						(if (Btst fTalkedToBear)
							(HighPrint 14 32)
							;Hi there!  I represent Ditto Realty.
							;We have some LOVELY properties that are PROVEN bargains.
							;We know that's true, because we've sold them so many times.
						else
							(Bset fTalkedToBear)
							(HighPrint 14 33)
							;You know that bears can't talk!
						)
					)
					(
						(or
							(Said 'feed/bear')
							(Said 'gave,throw/food,ration[/bear,creature]')
						)
						(cond 
							(hurtBear
								(HighPrint 14 34)
								;The Flame Dart has spoiled the bear's appetite.
								)
							((ego has: iRations)
								(if (not (if (> (ego x?) 180) (> (ego y?) 132)))
									(NotClose)
								else
									(ego use: iRations)
									(HighPrint 14 35)
									;Its hunger diminished, the bear takes a new attitude toward you.
									(Bset fBearFriendly)
									(SolvePuzzle f14CalmBear 5)
								)
							)
							(else
								(DontHave)
							)
						)
					)
					((Said 'kill,chop,beat,fight')
						(curRoom newRoom: vBear)
					)
					(
						(or
							(Said
								'free,unlock,open/bear,animal,creature,hasp,chain,manacle'
							)
							(Said 'use/key')
							(Said 'put,fill<in/key/hasp,chain,manacle')
						)
						(cond 
							((not (ego has: iBrassKey))
								(HighPrint 14 36)
								;You need to find the key.
								)
							(
								(not
									(ego
										inRect: [local50 0] [local50 1] [local50 2] [local50 3]
									)
								)
								(NotClose)
							)
							((or dazzledBear calmedBear (Btst fBearFriendly))
								(SolvePuzzle f14FreeBear 25)
								(self setScript: useKey)
							)
							(else
								(HighPrint 14 37)
								;The bear won't let you near the lock.
							)
						)
					)
					((Said 'lockpick/hasp,manacle,chain')
						(HighPrint 14 38)
						;Sorry, this lock is enchanted.  You'll need a magical key.
					)
				)
			)
		)
		(super handleEvent: event)
	)
	
	(method (getHurt damage)
		(cond 
			((<= (-= monsterHealth damage) 0)
				(Bset fBearDying)
				(curRoom newRoom: 171)
			)
			((or (== bearState bearNEUTRAL) (== bearState bearDOCILE))
				(bear setScript: bearUp)
			)
			(else
				(bear setLoop: 1 cel: 0 setCycle: EndLoop)
			)
		)
	)
)

(instance drip of Prop
	(properties
		y 204
		x 79
		view vKoboldCave
	)
)

(instance entranceMsg of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: 57 189 setMotion: MoveTo 60 178)
				(= cycles 15)
			)
			(1
				(ego loop: loopE)
				(= cycles 5)
			)
			(2
				(if (and (not (Btst fBearGone)) (not (Btst fBearCaveMessage)))
					(HighPrint 14 39)
					;As your eyes adjust from sunlight to darkness, you examine the interior of this eerie cavern.
					;You sense something moving off to your right.
					(Bset fBearCaveMessage)
				)
				(HandsOn)
			)
		)
	)
)

(instance bearUp of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(= bearState bearUPRIGHT)
				(bear setCycle: EndLoop self)
			)
			(1
				(if (and (not hurtBear) (not (Btst fMetBear)))
					(HighPrint 14 40)
					;A very large bear rears up as you approach.  It looks hungry and dangerous.
					(Bset fMetBear)
				)
				(= bearState bearTHREATEN)
			)
		)
	)
)

(instance bearDrop of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== bearState bearUPRIGHT)
					(= bearState bearDOCILE)
					(bear setCycle: BegLoop self)
				else
					(= bearState bearDOCILE)
					(bear setLoop: 0 cel: bearCel setCycle: BegLoop self)
				)
			)
			(1 (= bearState bearNEUTRAL))
		)
	)
)

(instance flameDart of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(++ hurtBear)
				;CI: NOTE: Is this an error? Shouldn't it be TrySkill MAGIC?  MANA should only be affected indirectly.
				;EO: Yes and no. It's an original scripting error, but it's fixed now.
				(TrySkill MAGIC 0 5)
				(TrySkill FLAMEDART 0 5)
				(ego
					view: vEgoMagicFlameDart
					setLoop: (if (< (ego x?) (bear x?)) 0 else 1)
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(1
				(egoShoots play:)
				(ego setCycle: EndLoop)
				(= local10 (Random 0 300))
				(if
					(or
						(< local10 (= distFromBear (ego distanceTo: bear)))
						(== bearState bearUPRIGHT)
						(== bearState bearDOCILE)
					)
					(dart setScript: bouncer)
					(self changeState: 3)
				else
					(dart
						setLoop: 2
						setStep: 18 12
						setPri: 12
						ignoreActors: 1
						posn: (ego x?) (ego y?)
						show:
						setCycle: Forward
						startUpd:
					)
					(++ burnedBear)
					(dart
						setMotion:
							MoveTo
							(+ (bear x?) (- (Random 0 10) 10))
							(+ (bear y?) (- (Random 0 17) 19))
							self
					)
					(bear getHurt: (+ 5 (/ [egoStats STR] 10)))
				)
			)
			(2
				(magicHit play:)
				(dart setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(3
				(NormalEgo)
				(ego illegalBits: (| cWHITE cYELLOW))
				(Face ego bear)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance bouncer of Script
	(method (doit)
		(if (and local15 (not (dart inRect: 10 35 310 205)))
			(= local15 0)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local16 (Random 3 5))
				(dart posn: (ego x?) (ego y?))
				(self cue:)
			)
			(1
				(= local15 1)
				(localproc_0e88)
				(dart
					show:
					setLoop: 2
					setStep: 18 12
					setPri: 12
					ignoreActors:
					setCycle: Forward
					startUpd:
					setMotion: MoveTo dartX dartY
				)
			)
			(2
				(puff
					ignoreActors:
					cel: 0
					setPri: 12
					posn: (dart x?) (dart y?)
					setCycle: EndLoop
				)
				(= local16 (+ local16 (Random 1 3)))
				(dart
					setMotion: MoveTo [local17 local16] [local28 local16] self
				)
			)
			(3
				(if (< local16 10)
					(self changeState: 2)
				else
					(self cue:)
				)
			)
			(4
				(dart setLoop: 3 cel: 0 setMotion: 0 setCycle: EndLoop self)
			)
			(5 (dart setScript: 0))
		)
	)
)

(instance bearKills of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 3)
			)
			(1
				(bear loop: 2 cel: 0 setCycle: EndLoop)
				(= cycles 1)
			)
			(2
				(bear loop: 3 cel: 0 setCycle: EndLoop)
				(ego
					view: vEgoDefeatedMagic
					loop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(3
				(= cycles 12)
			)
			(4
				(EgoDead 14 41
					#title {OH NOOOOOOO!}
					#icon vDeathScenes 0 0)
					;Boy, that smarts!  Your last thoughts, although trivial, gently nudge you toward infinity.
					;"I wonder if the bear's paws were clean.........."
			)
		)
	)
)

(instance bearKillsRm15 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: TRUE setMotion: MoveTo 235 (ego y?) self)
			)
			(1
				(bear loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(bear loop: 3 cel: 0 setCycle: EndLoop)
				(ego
					view: vEgoDefeatedMagic
					loop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(3 (= cycles 15))
			(4
				(EgoDead 14 42
					#title {OH NOOOOOOO!}
					#icon vDeathScenes 0 0)
					;Boy, that smarts!  Your last thoughts, although trivial, gently loft you toward infinity.
					;"I wonder if the bear's paws were clean.........."
			)
		)
	)
)

(instance useKey of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego use: iBrassKey)
				(HandsOff)
				(= bearState bearFREE)
				(bear setCycle: EndLoop self)
			)
			(1
				(ego
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 209 149 self
				)
			)
			(2
				(bear stopUpd:)
				(ego setMotion: MoveTo (- (bear x?) 22) (bear y?) self)
			)
			(3
				(ego view: vEgoThrowing loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(4
				(HighPrint 14 43)
				;The Kobold's key disappears as you turn it in the lock.
				(cSound stop:)
				(NormalEgo)
				(Bset fSavedBarnard)
				(curRoom newRoom: 171)
			)
		)
	)
)

(instance dripScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(drip
					posn: [dripX dripIndex] [dripY dripIndex]
					setCycle: EndLoop
				)
				(= cycles (Random 20 40))
			)
			(1
				(= dripIndex (Random 0 4))
				(self changeState: 0)
			)
		)
	)
)
