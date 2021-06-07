;;; Sierra Script 1.0 - (do not remove this comment)
(script# 52)
(include game.sh)
(use Main)
(use HighLite)
(use Intrface)
(use RFeature)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room52 0
)
(synonyms
	(body body actress c gertie ethel fifi attorney butler)
	(rotate wind)
	(detach loosen)
	(room basement)
)

(local
	scurryXY = [
		70 151
		187 161
		187 161
		70 151
		70 151
		208 111
		208 111
		70 151
		]
	i
	local17
	local18
	doorState
	local20
	local21
)
(instance Room52 of Room
	(properties
		picture 52
	)
	
	(method (init)
		(= horizon 0)
		(super init:)
		(addToPics add: rags tub eachElementDo: #init doit:)
		(|= deadGuests $003f)
		(self
			setRegions: 242
			setFeatures:
				rags
				tub
				WallPlate
				Chute
				Bricks1
				Bricks2
				Bricks3
				Bricks4
				Bricks5
				Walls
				Beams
				Floor1
				Floor2
		)
		(LoadMany VIEW 51 924)
		(LoadMany SOUND 45 84 126)
		(if howFast
			(Splash1 ignoreActors: TRUE setPri: 3 init: hide:)
			(Splash2 ignoreActors: TRUE setPri: 3 init: hide:)
		)
		(rat
			setStep: 5 5
			setScript: Scurry
			setCycle: Walk
			ignoreActors: TRUE
			init:
			hide:
		)
		(bodies setPri: 14 init: stopUpd:)
		(drip setPri: 3 cycleSpeed: 1 setScript: Drip init:)
		(panelCrank setPri: 8 init:)
		(if (== ((inventory at: iCrank) owner?) curRoomNum)
			(panelCrank stopUpd:)
			(ego observeBlocks: cBlock)
		else
			(panelCrank hide:)
		)
		(door setPri: 4 init: stopUpd:)
		(if (& global205 $0001)
			(door cel: (- (NumCels door) 1))
		else
			(door cel: 0)
			(ego illegalBits: (| cWHITE cBLUE))
		)
		(if (== global189 51)
			(ego setPri: 3 posn: 195 63)
		else
			(ego posn: 69 103)
		)
		(ego view: 7 init:)
		(glow deltaX: 8 deltaY: 8 ignoreCast: 1 init:)
	)
	
	(method (doit)
		(if (FirstEntry)
			(Print 52 0)
		)
		(if
			(and
				(& (ego onControl: FALSE) cBROWN)
				(!= (ego mover?) 0)
				howFast
			)
			(switch (ego loop?)
				(2
					(if (== (ego cel?) 2)
						(Splash1
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
				(3
					(if (== (ego cel?) 2)
						(Splash1
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
				(else 
					(if (== (ego cel?) 0)
						(Splash1
							posn: (- (ego x?) 2) (+ (ego y?) 1)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 4)
						(Splash2
							posn: (- (ego x?) 2) (+ (ego y?) 1)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
			)
		)
		(cond 
			((and (& global205 $0002) (bodies cel?))
				(= local20 1)
			)
			(
				(and
					(== (bodies cel?) (- (NumCels bodies) 1))
					(not (& global205 $0002))
				)
				(soundFX number: 53 loop: 1 play:)
				(= theTalker 25)
				(Say 0 52 1)
				(Print 52 2)
				(|= global205 $0002)
				(= global200 101)
			)
			(else
				(= local20 0)
			)
		)
		(switch (ego onControl: origin)
			(cBLUE
				(ego illegalBits: cWHITE)
				(= global189 51)
				(curRoom newRoom: 55)
			)
			(cGREEN
				(= global189 52)
				(curRoom newRoom: 55)
			)
			(cLCYAN
				(bodies cel: 1 forceUpd:)
			)
			(cLRED
				(bodies cel: 2 forceUpd:)
			)
			(cLMAGENTA
				(bodies cel: 3 forceUpd:)
			)
			(cYELLOW
				(bodies cel: 4 forceUpd:)
			)
			(cBLACK
				(bodies cel: 0 forceUpd:)
			)
			(cRED
				(bodies cel: 0 forceUpd:)
			)
		)
		(if (< (ego y?) 66)
			(ego setPri: 3)
		else
			(ego setPri: -1)
		)
		(if
			(or
				(< (ego y?) 66)
				(and (& global205 $0001) (& (ego onControl:) (| cGREEN cRED)))
			)
			(glow show:)
		else
			(glow hide:)
		)
		(cond 
			((< (ego x?) 110) (= vertAngle 30))
			((< (ego x?) 160) (= vertAngle 0))
			(else (= vertAngle 150))
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript AVOIDER)
		(DisposeScript 214)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0 [str 250])
		(if (event claimed?) (return))
		(if (== (event type?) saidEvent)
			(cond 
				((ego has: iCrank)
					(cond 
						((Said 'attach/control')
							(= doorState 1)
						)
						(
							(and
								(Said '/panel,door>')
								(or (Said 'open<control') (Said 'open//control'))
							)
							(= doorState 3)
						)
					)
				)
				((== ((inventory at: iCrank) owner?) curRoomNum)
					(cond 
						((Said 'attach/control')
							(Print 52 3)
						)
						((Said 'rotate/control,handle')
							(= doorState 2)
						)
						((Said 'open/panel,door')
							(if (& global205 $0001)
								(AlreadyOpen)
							else
								(Print 52 4)
							)
						)
						((Said 'get,detach,drag/control')
							(= doorState 4)
						)
						((Said 'examine/control')
							(Print
								(Format @str 52 5 52 6)
								#icon 624 0 1
							)
						)
					)
				)
				(
					(and
						(not (Said 'examine/control>'))
						(Said '*/control')
					)
					(DontHave)
				)
			)
			(if (not (event claimed?))
				(cond 
					((Said 'attach/control')
						(Print 52 7)
					)
					((Said 'close/panel,door')
						(if (& global205 $0001)
							(Print 52 4)
						else
							(AlreadyClosed)
						)
					)
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]')
								(if (not (& global205 $0002))
									(Print 52 0)
								else
									(Print 52 8)
								)
							)
							((or (Said 'examine/dirt') (Said 'examine<down'))
								(Print 52 9)
							)
							((or (Said 'examine/ceiling') (Said 'examine<up'))
								(Print 52 10)
							)
							((Said '/brick,(wall<brick)')
								(Print 52 11)
							)
							((Said '/passage')
								(if (& global205 $0001)
									(Print 52 12)
								else
									(Print 52 13)
								)
							)
							((Said '/water,mud')
								(Print 52 14)
							)
						)
					)
					(
						(or
							(Said 'get,detach,find/brick[<loose][/wall]')
							(Said 'break/wall[<brick]')
						)
						(Print 52 15)
					)
					((Said 'search,get,detach,move/body[/pile[<from]]')
						(if (& global205 $0002)
							(Print 52 16)
						else
							(DontSee)
						)
					)
				)
			)
			(if (and doorState (not local17))
				(panelCrank setScript: CrankIt)
			)
		)
	)
	
	(method (newRoom n)
		(drip setScript: 0)
		(super newRoom: n)
	)
)

(instance Drip of Script
	
	(method (doit)
		(if
		(and (== (drip cel?) 5) (== (soundFX state?) 0))
			(soundFX
				number: (+ 35 (* 10 (Random 0 1)))
				loop: 1
				play:
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 5 10))
			)
			(1
				(drip setCycle: EndLoop self)
				(= state -1)
			)
		)
	)
)

(instance Scurry of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 10 18)))
			(1
				(= i (* (Random 0 3) 4))
				(rat
					setLoop: (if (< [scurryXY i] [scurryXY (+ i 2)])
						2
					else
						3
					)
					posn: [scurryXY i] [scurryXY (++ i)]
					setMotion: MoveTo [scurryXY (++ i)] [scurryXY (++ i)] self
					show:
				)
				(soundFX
					number: (if (== (rat loop?) 2) 84 else 58)
					loop: -1
					play:
				)
			)
			(2
				(rat hide:)
				(soundFX fade:)
				(= state -1)
				(= cycles 1)
			)
		)
	)
)

(instance CrankIt of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego inRect: 40 124 70 160)
					(= cycles 1)
				else
					(ego
						setAvoider: (Avoider new:)
						setMotion: MoveTo 85 120 self
					)
				)
				(= local17 1)
			)
			(1
				(ego setMotion: MoveTo 57 126 self)
			)
			(2
				(crankCage init:)
				(ego loop: 3 setAvoider: 0 observeBlocks: crankCage hide:)
				(panelCrank
					view: 51
					loop: 0
					cel: 0
					posn: 57 126
					cycleSpeed: 1
					show:
				)
				(HandsOn)
				(= cycles 2)
			)
			(3
				(cond 
					((or (== doorState 1) (== doorState 3))
						(ego observeBlocks: cBlock)
						(Print 52 17 #at 90 55)
						((inventory at: iCrank) moveTo: curRoomNum)
						(if (== doorState 1) (= doorState 0) else (= state 4))
					)
					((== doorState 2)
						(if (not (& global205 $0001))
							(= state 4)
						else
							(Print 52 18 #at 90 55)
							(= state 6)
						)
					)
					((== doorState 4)
						(Print 52 18
							#at 90 55
						)
						(= state 6)
					)
				)
				(= cycles 1)
			)
			(4
				(if (and local17 (== (ego x?) 57) (== (ego y?) 126))
					(= state 2)
				else
					(= state 6)
				)
				(= cycles 1)
			)
			(5
				(HandsOff)
				(Print 52 19 #at 90 55)
				(soundFX number: 126 loop: 1 play:)
				(door cycleSpeed: 2 setCycle: EndLoop self)
				(panelCrank setCycle: Forward)
			)
			(6
				(panelCrank setCycle: EndLoop self)
				(|= global205 $0001)
			)
			(7
				(cls)
				(User controls: TRUE canInput: TRUE)
				(panelCrank view: 152 loop: 2 cel: 2 posn: 67 90)
				(ego
					setLoop: -1
					posn: 57 126
					ignoreBlocks: crankCage
					observeBlocks: cBlock
					illegalBits: cWHITE
					show:
					setCycle: Walk
				)
				(= doorState (= local17 0))
				(client setScript: 0)
			)
		)
	)
)

(instance WallPlate of RFeature
	(properties
		nsTop 86
		nsLeft 55
		nsBottom 91
		nsRight 58
	)
	
	(method (handleEvent event &tmp [str 75])
		(if (or (MousedOn self event shiftDown) (Said 'examine/nameplate'))
			(event claimed: TRUE)
			(if (== ((inventory at: iCrank) owner?) curRoomNum)
				(Print
					(Format @str 52 5 52 6)
					#icon 624 0 1
				)
			else
				(Print
					(Format @str 52 20 52 6)
					#icon 624 0 0
				)
			)
		)
		(cond 
			(
				(or
					(Said 'use/key')
					(Said 'attach/key')
					(Said 'unbar/nameplate')
				)
				(if (or (ego has: iSkeletonKey) (ego has: iBrassKey))
					(Print 52 21)
				else
					(DontHave)
				)
			)
			((Said 'attach/poker')
				(if (ego has: iPoker)
					(Print 52 22)
				else
					(DontHave)
				)
			)
			((Said 'attach/crowbar')
				(if (ego has: iCrowbar)
					(Print 52 22)
				else
					(DontHave)
				)
			)
			((Said 'attach/key')
				(if (or (ego has: iBrassKey) (ego has: iSkeletonKey))
					(Print 52 22)
				else
					(DontHave)
				)
			)
			((Said 'force/nameplate')
				(if (ego has: iCrowbar)
					(Print 52 23)
				else
					(DontHave)
				)
			)
		)
	)
)

(instance Chute of RFeature
	(properties
		nsTop 71
		nsLeft 34
		nsBottom 104
		nsRight 54
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(MousedOn self event shiftDown)
					(Said 'examine[<up]/chute[<laundry]')
				)
				(event claimed: TRUE)
				(Print 52 24)
			)
			((Said '(enter,go,get,climb)[<(up,in)]/chute[<laundry]')
				(Print 52 25)
			)
		)
	)
)

(instance Bricks1 of RFeature
	(properties
		nsTop 105
		nsLeft 34
		nsBottom 126
		nsRight 42
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 52 11)
		)
	)
)

(instance Bricks2 of RFeature
	(properties
		nsTop 49
		nsLeft 58
		nsBottom 109
		nsRight 73
	)
	
	(method (handleEvent event)
		(cond 
			((MousedOn self event shiftDown)
				(event claimed: TRUE)
				(if (& global205 $0001)
					(Print 52 26)
				else
					(Print 52 11)
				)
			)
			((Said 'examine/door,panel[<hidden,hidden]')
				(if (& global205 $0001)
					(Print 52 26)
				else
					(DontSee)
				)
			)
			(
				(and
					(& global205 $0001)
					(Said 'examine/archway[<hidden,hidden]')
				)
				(Print 52 26)
			)
		)
	)
)

(instance Bricks3 of RFeature
	(properties
		nsTop 49
		nsLeft 82
		nsBottom 64
		nsRight 89
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 52 11)
		)
	)
)

(instance Bricks4 of RFeature
	(properties
		nsTop 15
		nsLeft 81
		nsBottom 34
		nsRight 87
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 52 11)
		)
	)
)

(instance Bricks5 of RFeature
	(properties
		nsTop 49
		nsLeft 211
		nsBottom 60
		nsRight 221
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 52 11)
		)
	)
)

(instance Walls of RFeature
	(properties
		nsLeft 96
		nsBottom 35
		nsRight 202
	)
	
	(method (handleEvent event)
		(cond 
			((MousedOn self event shiftDown)
				(event claimed: TRUE)
				(Print 52 27)
			)
			((Said 'examine/wall')
				(if (& global205 $0001)
					(Print 52 28)
				else
					(Print 52 29)
				)
			)
		)
	)
)

(instance Beams of RFeature
	(properties
		nsTop 36
		nsBottom 48
		nsRight 237
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/beam'))
			(event claimed: TRUE)
			(Print 52 30)
		)
	)
)

(instance Floor1 of RFeature
	(properties
		nsTop 50
		nsLeft 96
		nsBottom 73
		nsRight 200
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 52 31)
		)
	)
)

(instance Floor2 of RFeature
	(properties
		nsTop 74
		nsLeft 75
		nsBottom 137
		nsRight 180
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {floor})
		)
	)
)

(instance rags of RPicView
	(properties
		y 164
		x 203
		view 152
		loop 2
		cel 1
		priority 9
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((MousedOn self event shiftDown)
				(event claimed: TRUE)
				(ParseName {equipment})
			)
			((Said 'scrub/cloth')
				(Print 52 32)
			)
			((Said 'get/cloth')
				(Print 52 33)
			)
			((Said 'examine/cloth')
				(Print 52 34)
			)
		)
	)
)

(instance tub of RPicView
	(properties
		y 166
		x 188
		view 152
		loop 2
		priority 14
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'rotate,examine/bath,equipment[<laundry,scrub]')
				(Print 52 35)
			)
			((MousedOn self event shiftDown)
				(event claimed: TRUE)
				(ParseName {equipment})
			)
		)
	)
)

(instance door of Prop
	(properties
		y 49
		x 60
		view 152
		signal ignrAct
	)
)

(instance bodies of Prop
	(properties
		y 148
		x 69
		view 152
		loop 3
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((or (Said '/body,pile>') (Said '/pile/body[<dead]>'))
				(if (Said '(examine<in),search,get')
					(if (or local20 (& global205 $0002))
						(Print 52 16)
					else
						(DontSee)
					)
				)
				(if (Said 'examine[<at]')
					(if local20
						(Print 52 36)
					else
						(Print 52 37)
					)
				)
			)
			((and local20 (MousedOn self event shiftDown))
				(event claimed: TRUE)
				(Print 52 36)
			)
		)
	)
)

(instance panelCrank of Prop
	(properties
		y 90
		x 67
		view 152
		loop 2
		cel 2
		signal ignrAct
	)
	
	(method (handleEvent event)
		(if (and (not local17) (MousedOn self event shiftDown))
			(event claimed: TRUE)
			(ParseName {crank})
		)
	)
)

(instance soundFX of Sound
	(properties
		number 53
	)
)

(instance drip of Prop
	(properties
		y 63
		x 117
		view 152
		loop 1
		signal ignrAct
	)
)

(instance rat of Actor
	(properties
		view 151
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(cond 
			((Said '/mouse>')
				(cond 
					((Said 'examine')
						(if (rat mover?)
							(Print 52 38)
						else
							(DontSee)
						)
					)
					((Said 'get,capture')
						(if (rat mover?)
							(Print 52 39)
						else
							(DontSee)
						)
					)
					((Said 'kill')
						(if (rat mover?)
							(Print 52 40)
						else
							(DontSee)
						)
					)
				)
			)
			((and (rat mover?) (MousedOn self event shiftDown))
				(event claimed: TRUE)
				(Print 52 38)
			)
		)
	)
)

(instance glow of HighLite)

(instance Splash1 of Prop
	(properties
		view 1
		loop 6
	)
)

(instance Splash2 of Prop
	(properties
		view 1
		loop 6
	)
)

(instance cBlock of Block
	(properties
		top 117
		left 54
		bottom 119
		right 63
	)
)

(instance crankCage of Cage
	(properties
		top 120
		left 42
		bottom 142
		right 80
	)
)
