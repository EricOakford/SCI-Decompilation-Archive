;;; Sierra Script 1.0 - (do not remove this comment)
(script# 75)
(include game.sh)
(use Main)
(use Intrface)
(use Block)
(use LoadMany)
(use DPath)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm75 0
)

(local
	[local0 2]
	talkedToRat
	[local3 2]
	ratGone
	ratWet
	nearRat
	ratOffering
	pointsLost
)
(instance ratBlock of Block
	(properties
		top 136
		left 36
		bottom 147
		right 86
	)
)

(instance rm75 of Room
	(properties
		picture 75
		east 74
		west 76
	)
	
	(method (init)
		(Load VIEW 275)
		(Load SOUND 15)
		(if (not (= ratGone (Btst fGaveRatSomething)))
			(LoadMany VIEW 3 20 105 106)
			(LoadMany SOUND 45 67 46 43)
			((ScriptID 0 21) number: 45 init: loop: -1 play:)
			(if (and (ego has: iWaterBucket) (Btst fWaterInBucket))
				(Load VIEW 50)
			)
		)
		(self style:
		(switch prevRoomNum
			(west WIPERIGHT)
			(east WIPELEFT)
		))
		(super init:)
		(if (not ratGone)
			(= talkedToRat FALSE)
			(rat setPri: 10 ignoreActors: init: stopUpd:)
			(if (>= howFast 1)
				(rat setScript: ratBlinkScript)
			)
			(ego observeBlocks: ratBlock)
			(ratEyes
				posn: (+ (rat x?) 18) (- (rat y?) 37)
				setPri: 11
				init:
				hide:
				stopUpd:
			)
			(eyeBalls
				posn: (ratEyes x?) (ratEyes y?)
				setPri: 11
				init:
			)
			(tongue
				setPri: 11
				posn: (+ (rat x?) 12) (- (rat y?) 35)
				init:
				hide:
				stopUpd:
			)
			(if (>= howFast 1)
				(tongue setScript: tongueScript)
			)
			(slobber
				setPri: 10
				posn: (+ (rat x?) 12) (- (rat y?) 35)
				init:
				hide:
				stopUpd:
			)
			(if (>= howFast 1)
				(slobber setScript: slobberScript)
			)
			(ratGut
				posn: (+ (rat x?) 5) (- (rat y?) 12)
				cycleSpeed: 2
				setPri: 10
				init:
				stopUpd:
			)
			(if (>= howFast 1)
				(ratGut setCycle: Forward)
			)
			(ratFightCloud init: hide: stopUpd:)
		)
		(door ignoreActors: setPri: 9 init: stopUpd:)
		(switch prevRoomNum
			(west
				(ego posn: 34 144)
				(door setScript: closeDoor)
				(Bset fGaveRatSomething)
			)
			(else 
				(ego posn: 317 (proc0_17 166 (ego y?) 134))
			)
		)
		(ego init:)
		(NormalEgo)
		(torchLight setPri: 4 init: stopUpd:)
		(if (>= howFast 1) (torchLight setCycle: Forward))
		(if haloTimer
			(= haloTimer (Random 10 40))
		)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			(script
				(script doit:)
			)
			(
				(and
					(not ratGone)
					(not nearRat)
					(== (ego onControl: origin) cBROWN)
				)
				(ego setMotion: 0)
				(if (Btst fInvisible)
					(Print 75 0)
				else
					(Print 75 1)
				)
				(Print 75 2)
				(= nearRat TRUE)
			)
			((and (not ratGone) (== (ego onControl: origin) cGREEN))
				(curRoom setScript: fightRat)
			)
			((== (ego onControl: origin) cRED)
				(curRoom setScript: openDoor)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self newRoom: nRoom)
			)
		)
	)
	
	(method (handleEvent event &tmp i)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((and ratGone (or (Said '/rat') (Said '//rat')))
				(Print 75 3)
			)
			((Said 'trade,bargain//rat<with')
				(Print 75 4)
			)
			((or (Said '/mouse') (Said '//mouse'))
				(if ratGone
					(Print 75 5)
				else
					(Print 75 6)
				)
			)
			(
				(or
					(Said 'look,check/rat')
					(MousedOn rat event shiftDown)
					(MousedOn tongue event shiftDown)
					(MousedOn ratGut event shiftDown)
					(MousedOn ratEyes event shiftDown)
				)
				(Print 75 7)
				(event claimed: TRUE)
			)
			(
			(or (Said '/rat>') (Said '//rat>') (Said '/*<rat>'))
				(cond 
					((Said 'bite')
						(Print 75 8)
					)
					((Said 'talk,speak')
						(if talkedToRat
							(Print 75 9)
						else
							(= talkedToRat TRUE)
							(if (Btst fInvisible)
								(Print 75 10)
							else
								(Print 75 11)
							)
							(Print 75 12)
						)
					)
					((Said 'kill,shoot,attack,kill/')
						(Print 75 13)
					)
					((Said 'ask/[rat]/door')
						(Print 75 14)
					)
					((Said 'show/')
						(cond 
							((Btst fInvisible)
								(Print 75 15)
							)
							((Said 'cheese')
								(if (ego has: iCheese)
									(Print 75 16)
								else
									(DontHave)
								)
							)
							(else
								(Print 75 17)
							)
						)
					)
					((Said 'throw,throw/boulder,pebble')
						(cond 
							((and (ego has: iPebbles) (not (Btst fGaveRatSomething)))
								(PebbleCount)
								(if (== numPebbles 0)
									(Print 75 18)
								else
									(Print 75 19)
								)
							)
							((and (Btst fGaveRatSomething) (ego has: iPebbles))
								(Print 75 20)
							)
							(else
								(Print 75 21)
							)
						)
					)
					((Said 'throw/bucket,water')
						(cond 
							((or (not (ego has: iWaterBucket)) (not (Btst fWaterInBucket)))
								(Print 75 22)
							)
							((> (ego distanceTo: rat) 70)
								(CantReach)
							)
							((curRoom script?)
								(CantDo)
							)
							(else
								(curRoom setScript: waterRat)
							)
						)
					)
					((Said 'capture,attack,pet,touch')
						(cond 
							((curRoom script?)
								(CantDo)
							)
							((> (ego distanceTo: rat) 65)
								(Print 75 23)
								(Print 75 24)
							)
							(else
								(curRoom setScript: fightRat)
							)
						)
					)
					((Said 'throw/dagger')
						(cond 
							((not (ego has: iDagger))
								(DontHave)
							)
							((not (Btst fGaveRatSomething))
								(Print 75 25)
							)
							(else (Print 75 26)
								(event claimed: TRUE)
							)
						)
					)
					((Said 'feed>')
						(cond 
							((Said '/cheese')
								(if (not (ego has: iCheese))
									(DontHave)
								else
									(= pointsLost 0)
									(= ratOffering iCheese)
								)
							)
							((Said '/egg')
								(if (not (ego has: iGoldEgg))
									(DontHave)
								else
									(= pointsLost 6)
									(= ratOffering iGoldEgg)
								)
							)
							((and (Said '/nut,nut') (Btst fOpenedWalnut))
								(if (not (ego has: iWalnut))
									(DontHave)
								else
									(= pointsLost 6)
									(= ratOffering iWalnut)
								)
							)
						)
						(cond 
							((not ratOffering) 0)
							((> (ego distanceTo: rat) 80)
								(Print 75 27)
							)
							((curRoom script?)
								(CantDo)
							)
							(else
								(curRoom setScript: grabScript)
							)
						)
					)
					((Said 'give>')
						(cond 
							((Said '/treasure')
								(= pointsLost 0)
								(cond 
									((ego has: iMagicMirror)
										(ego put: iMagicMirror)
										(= pointsLost 8)
									)
									((ego has: iMagicShield)
										(ego put: iMagicShield)
										(= pointsLost 8)
									)
									((ego has: iChest)
										(ego put: iChest)
										(= pointsLost 8)
									)
									((ego has: iPouch)
										(ego put: iPouch)
										(if (Btst fOpenedPouch)
											(= pointsLost 6)
										else
											(= pointsLost 3)
										)
									)
									((ego has: iWalnut)
										(ego put: iWalnut)
										(if (Btst fOpenedWalnut)
											(= pointsLost 6)
										else
											(= pointsLost 3)
										)
									)
									((ego has: iGoldEgg)
										(ego put: iGoldEgg)
										(= pointsLost 6)
									)
									((ego has: iSceptre)
										(ego put: iSceptre)
										(= pointsLost 6)
									)
								)
								(if (== pointsLost 0)
									(Print 75 28)
								else
									(curRoom setScript: grabScript)
								)
							)
							((Said '/diamond,bag')
								(if (not (ego has: iPouch))
									(DontHave)
								else
									(if (Btst fOpenedPouch)
										(= pointsLost 3)
									else
										(= pointsLost 6)
									)
									(= ratOffering iPouch)
								)
							)
							((Said '/mirror')
								(if (not (ego has: iMagicMirror))
									(DontHave)
								else
									(= pointsLost 8)
									(= ratOffering iMagicMirror)
								)
							)
							((Said '/chest')
								(if (not (ego has: iChest))
									(DontHave)
								else
									(= pointsLost 8)
									(= ratOffering iChest)
								)
							)
							((Said '/ring')
								(if (not (ego has: iMagicRing))
									(DontHave)
								else
									(= pointsLost 3)
									(= ratOffering iMagicRing)
									(Bclr fWearingRing)
									(Bclr fInvisible)
									(NormalEgo)
								)
							)
							((Said '/cheese')
								(if (not (ego has: iCheese))
									(DontHave)
								else
									(= pointsLost 0)
									(= ratOffering iCheese)
								)
							)
							((Said '/egg')
								(if (not (ego has: iGoldEgg))
									(DontHave)
								else
									(= pointsLost 6)
									(= ratOffering iGoldEgg)
								)
							)
							((and (Said '/nut,nut') (Btst fOpenedWalnut))
								(if (not (ego has: iWalnut))
									(DontHave)
								else
									(= pointsLost 6)
									(= ratOffering iWalnut)
								)
							)
							(
								(and
									(= i (inventory firstTrue: #saidMe))
									(i ownedBy: ego)
								)
								(Print 75 29)
							)
							(else
								(event claimed: FALSE)
							)
						)
						(cond 
							((not ratOffering) 0)
							((> (ego distanceTo: rat) 80)
								(Print 75 30)
							)
							((curRoom script?)
								(CantDo)
							)
							(else
								(curRoom setScript: grabScript)
							)
						)
					)
				)
			)
			((Said 'bite')
				(Print 75 31)
			)
			((Said 'talk,speak')
				(Print 75 32)
			)
			((Said 'throw')
				(Print 75 33)
			)
			((Said 'attack,pet,touch')
				(Print 75 34)
			)
			((Said 'count/finger')
				(cond 
					((Btst fInvisible)
						(Print 75 35)
					)
					(ratGone
						(Print 75 36)
					)
					(else
						(Print 75 37)
					)
				)
			)
			((Said 'listen')
				(if ratGone
					(Print 75 38)
				else
					(Print 75 39)
				)
			)
			((Said 'smell,sniff')
				(Print 75 40)
			)
			((Said 'get,take,drink/water,drink')
				(Print 75 41)
			)
			((Said 'light/torch,match')
				(Print 75 42)
			)
			((or (Said '/wall[<cave]>') (and (Said '/cave>') (Said '/side>')))
				(cond 
					((Said 'look,check')
						(Print 75 43)
					)
					((Said 'climb,scale,ascend')
						(Print 75 44)
					)
				)
			)
			((Said '/boulder,mold>')
				(cond 
					((Said 'eat,consume')
						(Print 75 45)
					)
					((Said 'take')
						(Print 75 46)
					)
					((Said 'look,check')
						(Print 75 47)
					)
				)
			)
			((Said 'take,bend/stalactite,stalactite>')
				(Print 75 48)
			)
			((Said 'look,check>')
				(cond 
					((or (Said '/floor') (Said '<down'))
						(Print 75 49)
					)
					((Said '[<at,around][/room,cave]')
						(if (not ratGone)
							(Print 75 50)
							(Print 75 51)
						else
							(Print 75 52)
						)
					)
					((Said '/stalactite,stalactite')
						(Print 75 53)
					)
				)
			)
			((Said 'give,feed/*')
				(Print 75 54)
			)
			(
				(and
					(== (event type?) mouseDown)
					(& (event modifiers?) shiftDown)
					(== (OnControl PMAP (event x?) (event y?)) allEvents)
				)
				(Print 75 53)
			)
		)
	)
)

(instance doorSound of Sound
	(properties
		priority 10
	)
)

(instance torchLight of Prop
	(properties
		x 237
		y 112
		description {torch}
		view 275
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((Said 'take,get,take/torch')
				(Print 75 55)
			)
			(
				(or
					(MousedOn self event)
					(Said 'look,check/torch,candle,light,flame,fire')
				)
				(Print 75 56)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 31
		y 132
		view 275
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((or (Said 'look,check/door') (MousedOn self event shiftDown))
				(Print 75 57)
				(event claimed: TRUE)
			)
			((Said 'enter,open,open/door')
				(if (Btst fGaveRatSomething)
					(Print 75 58)
				else
					(Print 75 59)
				)
			)
		)
	)
)

(instance ratFightCloud of Actor
	(properties
		x 50
		y 150
		view 20
	)
	
	(method (doVerb)
	)
)

(instance rat of Prop
	(properties
		x 55
		y 150
		view 105
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(cond 
			((or (Said 'look,check/rat') (MousedOn self event shiftDown))
				(Print 75 60)
				(event claimed: TRUE)
			)
			((Said 'look,check/teeth')
				(Print 75 61)
			)
			((Said 'look,check/claw')
				(Print 75 62)
			)
		)
	)
)

(instance tongue of Prop
	(properties
		x 86
		y 115
		view 105
		loop 2
	)
)

(instance slobber of Prop
	(properties
		x 86
		y 115
		view 105
		loop 1
	)
)

(instance ratGut of Prop
	(properties
		x 79
		y 138
		description {rat gut}
		view 105
		loop 3
	)
)

(instance ratEyes of View
	(properties
		x 92
		y 113
		description {rat's eyes}
		view 105
		cel 1
	)
)

(instance eyeBalls of View
	(properties
		x 92
		y 113
		description {eyeball}
		view 105
		loop 5
	)
)

(instance waterRat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 50 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(1
				(= ratWet TRUE)
				(ratGut cycleSpeed: 1)
				(Print 75 63)
				(HandsOn)
				(NormalEgo)
				(ego loop: 1)
				(BucketState FALSE)
				(self dispose:)
			)
		)
	)
)

(instance grabScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== ratOffering iCheese)
					(Print 75 64)
				else
					(Print 75 65)
				)
				(= cycles 2)
			)
			(1
				((ScriptID 0 21) fade:)
				(ego setMotion: MoveTo 105 (rat y?) self)
			)
			(2
				(SetItemOwner ratOffering)
				(theGame changeScore: (- 0 pointsLost))
				(ego loop: 1 ignoreBlocks: ratBlock stopUpd:)
				(rat setScript: 0)
				(ratEyes dispose:)
				(eyeBalls dispose:)
				(tongue setScript: 0 dispose:)
				(ratGut dispose:)
				(slobber setScript: 0 dispose:)
				((ScriptID 0 21) number: 67 loop: 1 play:)
				(rat
					view: 106
					setLoop: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(3
				(cond 
					((Btst fInvisible)
						(if (== ratOffering iCheese)
							(Print 75 66)
							(SolvePuzzle fGaveRatCheese 2)
						else
							(Print 75 67)
						)
					)
					((== ratOffering iCheese)
						(Print 75 68)
						(SolvePuzzle fGaveRatCheese 2)
					)
					(else
						(Print 75 69)
					)
				)
				(self cue:)
			)
			(4
				((ScriptID 0 21) number: 43 loop: 1 play:)
				(rat setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(5
				(rat dispose:)
				(= ratGone TRUE)
				(Bset fGaveRatSomething)
				(HandsOn)
				(= cycles 2)
			)
			(6
				(self dispose:)
			)
		)
	)
)

(instance slobberScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(slobber show: setCycle: Forward)
				(= cycles (Random 40 60))
			)
			(1
				(slobber hide: stopUpd:)
				(= cycles (if ratWet (Random 8 15) else (Random 4 8)))
			)
			(2 (self changeState: 0))
		)
	)
)

(instance tongueScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(tongue cycleSpeed: 1 setCycle: Forward show:)
				(= cycles (Random 10 20))
			)
			(1
				(tongue hide: stopUpd:)
				(= cycles 1)
			)
			(2
				(tongue setCel: 0)
				(= seconds (Random 6 15))
			)
			(3 (self init:))
		)
	)
)

(instance ratBlinkScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(eyeBalls
			cel:
				(cond 
					((> (ego x?) 166) 0)
					((> (ego y?) (+ (rat y?) 5)) 2)
					((< (ego y?) (- (rat y?) 5)) 1)
					(else 0)
				)
			forceUpd:
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ratEyes show:)
				(eyeBalls hide: stopUpd:)
				(= cycles (Random 3 6))
			)
			(1
				(ratEyes hide: stopUpd:)
				(eyeBalls show:)
				(= seconds
					(if ratWet (Random 10 20) else (Random 2 5))
				)
			)
			(2 (self init:))
		)
	)
)

(instance fightRat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ratEyes hide: stopUpd:)
				(eyeBalls hide: stopUpd:)
				(tongue setScript: 0 hide: stopUpd:)
				(ratGut hide: stopUpd:)
				(slobber setScript: 0 hide: stopUpd:)
				(rat setLoop: 4 setScript: 0 setCycle: EndLoop self)
			)
			(1
				(rat hide:)
				(ego hide:)
				(ratFightCloud
					posn: (/ (+ (rat x?) (ego x?)) 2) (/ (+ (rat y?) (ego y?)) 2)
					setLoop: 2
					setCel: 0
					show:
				)
				((ScriptID 0 21) number: 46 loop: 1 play: self)
				(= cycles 2)
			)
			(2
				(rat stopUpd:)
				(ego stopUpd:)
				(if (Btst fInvisible) (Print 75 70))
				(ratFightCloud
					setLoop: 0
					setCycle: Forward
					show:
					setMotion: MoveTo (ego x?) (ego y?)
				)
				(= cycles 6)
			)
			(3
				(ratFightCloud
					setLoop: 0
					setMotion: MoveTo (/ (+ (rat x?) (- (ego x?) 35)) 2) (rat y?)
				)
			)
			(4
				(ego
					view: 3
					setLoop: 0
					setCel: 0
					posn: 82 160
					setCycle: 0
					show:
					stopUpd:
				)
				(rat setLoop: 0 setCel: 0 show: stopUpd:)
				(if (>= howFast 1) (rat setScript: ratBlinkScript))
				(ratEyes show: stopUpd:)
				(eyeBalls show: stopUpd:)
				(tongue show: stopUpd:)
				(if (>= howFast 1) (tongue setScript: tongueScript))
				(ratGut show: stopUpd:)
				(slobber show: stopUpd:)
				(if (>= howFast 1)
					(slobber setScript: slobberScript)
				)
				(ratFightCloud setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(5
				(ratFightCloud hide:)
				(= seconds 3)
			)
			(6
				(EgoDead
					{Rats!__This was one shady character you shouldn't have tangled with.}
				)
				(HandsOn)
			)
		)
	)
)

(instance openDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(doorSound number: 15 init: loop: 1 play:)
				(ego stopUpd:)
				(door cycleSpeed: 1 setCycle: EndLoop self)
			)
			(1
				(doorSound stop:)
				(ego setMotion: DPath 24 139 -1 139 self)
			)
			(2
				(HandsOn)
				(curRoom newRoom: (curRoom west?))
			)
		)
	)
)

(instance closeDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(doorSound number: 16 loop: 1 init: play:)
				(ego stopUpd:)
				(door cel: 4 setCycle: BegLoop self)
			)
			(1
				(doorSound stop:)
				(door stopUpd:)
				(self dispose:)
			)
		)
	)
)
