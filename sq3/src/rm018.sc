;;; Sierra Script 1.0 - (do not remove this comment)
(script# 18)
(include game.sh)
(use Main)
(use Intrface)
(use Wander)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm018 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	[local6 33]
	local39
	local40
	local41
)
(procedure (localproc_0e6a message theX theY)
	(Display message
		p_width 50
		p_at theX theY
		p_color vYELLOW
		p_font 600
	)
)

(procedure (localproc_0e85 message theX theY)
	(if message
		(Display message
			p_width 50
			p_at theX theY
			p_font 600
			p_color vBLACK
		)
		(vHair forceUpd:)
	)
)

(procedure (localproc_0eac)
	(FButton
		cel: (cond 
			((< shipShieldHealth 2) 2)
			((== shieldActivated 1) 1)
			(else 0)
		)
		draw:
	)
	(BButton
		cel: (cond 
			((< shipShieldHealth 2) 2)
			((== shieldActivated 2) 1)
			(else 0)
		)
		draw:
	)
	(switch shieldActivated
		(shieldOFF
			(frontShield cel: 0)
			(backShield cel: 0)
		)
		(shieldFRONT
			(frontShield cel: (if (< shipShieldHealth 6) 1 else 2))
			(backShield cel: 0)
		)
		(shieldBACK
			(frontShield cel: 0)
			(backShield cel: (if (< shipShieldHealth 6) 1 else 2))
		)
	)
)

(instance rm018 of Room
	(properties
		style HSHUTTER
	)
	
	(method (init &tmp [temp0 50])
		(if (> numColors 4)
			(self picture: 18)
		else
			(self picture: 180)
		)
		(self setRegions: TRAVEL)
		(if (> numColors 4)
			(Load VIEW 46)
		else
			(Load VIEW 146)
		)
		(Load VIEW 45)
		(Load VIEW 47)
		(Load VIEW 48)
		(Load VIEW 51)
		(Load VIEW 55)
		(Load PICTURE 121)
		(Load SOUND 27)
		(Load SOUND 39)
		(Load SOUND 31)
		(Load SOUND 33)
		(Load SOUND 200)
		(Load SOUND 201)
		(ego
			view: 45
			setLoop: 3
			setCel: 2
			setCycle: 0
			setStep: 4 4
			setPri: 10
			ignoreActors: TRUE
			posn: 160 88
			illegalBits: cYELLOW
			init:
			setCycle: 0
		)
		(hHair init: stopUpd:)
		(vHair init: stopUpd:)
		(engage init:)
		(super init:)
		(HandsOff)
		(= global592 1)
		(= local5 {NEGATIVE})
		(theGame setCursor: normalCursor (HaveMouse))
		(addToPics add: shipShape eachElementDo: #init)
		(addToPics doit:)
		(frontShield init:)
		(backShield init:)
		(controls
			add: FButton BButton HButton fire exit
			eachElementDo: #init
			draw:
		)
		(localproc_0eac)
		(Display 18 0
			p_at 44 110
			p_font 600
			p_width 50
			p_color vLGREEN
		)
		(= local3 0)
		(if global594 (= global175 2))
	)
	
	(method (doit &tmp [temp0 20])
		(if (and (< shipShieldHealth 2) (not local4))
			(= local4 1)
			(= shieldActivated 0)
			(localproc_0eac)
			(Depleted changeState: 1)
		)
		(if (== global175 1)
			(= global175 0)
			(zoomShip init: posn: 0 0)
			(curRoom
				setScript:
					(switch global594
						(0 ZoomScript)
						(1 ZoomScript)
						(2 ZScript)
					)
			)
		)
		(if local3
			(if (or (== local3 4) (== local3 5))
				(ego posn: (targShip x?) (targShip y?))
			)
			(if
				(and
					(cast contains: targShip)
					(ego
						inRect:
							(- (targShip x?) 5)
							(- (targShip y?) 4)
							(+ (targShip x?) 5)
							(+ (targShip y?) 4)
					)
					(== local3 3)
					(== global209 3)
				)
				(HandsOff)
				(= local3 4)
				(curRoom setScript: CenterScript)
			)
		)
		(if (== local3 3)
			(if global219 (-- local0))
			(if (not local0)
				(trackSound stop:)
				(curRoom setScript: OutOfRange)
			)
		)
		(if (== local3 5)
			(if global219 (-- local1))
			(if (not local1)
				(lockSound stop:)
				(curRoom setScript: OutOfRange)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look[/area]') (curRoom newRoom: 17))
	)
	
	(method (newRoom newRoomNumber)
		(User canControl: TRUE)
		(if (and global218 (< global175 10)) (= global175 10))
		(timers eachElementDo: #dispose 84)
		(super newRoom: newRoomNumber)
	)
)

(instance OutOfRange of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local3 1)
				(= local39 1)
				(lockSign setCel: 3 init: forceUpd:)
				(engage setCel: 0 stopUpd:)
				(targShip
					view: 48
					setLoop: 4
					setCel: 5
					setMotion: 0
					cycleSpeed: 0
					setCycle: BegLoop self
				)
			)
			(1
				(targShip dispose:)
				(topArrow dispose:)
				(bottomArrow dispose:)
				(leftArrow dispose:)
				(rightArrow dispose:)
				(ego setMotion: MoveTo 160 88 self)
			)
			(2
				(lockSign dispose:)
				(ego posn: 160 88 stopUpd:)
				(HandsOff)
				(vHair posn: 160 88 stopUpd:)
				(hHair posn: 160 88 stopUpd:)
				(= local39 0)
				(curRoom setScript: ZScript)
			)
		)
	)
)

(instance ZoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global594 1)
				(localproc_0e85 local5 40 125)
				(if global207
					(= local5 { IN REAR})
				else
					(= local5 {NEGATIVE})
				)
				(RadarScript changeState: 0)
				(= seconds 3)
			)
			(1
				(boom play:)
				(ShakeScreen 5 3)
				(if
				(or (not (-- shipShieldHealth)) (!= shieldActivated 2))
					(client setScript: BlowShip)
				else
					(zoomShip
						posn: 135 79
						setLoop: 2
						cel: 0
						cycleSpeed: 0
						setCycle: EndLoop self
					)
				)
			)
			(2
				(localproc_0e85 local5 40 125)
				(if global207
					(= local5 {IN FRONT})
				else
					(= local5 {NEGATIVE})
				)
				(RadarScript changeState: 0)
				(= global594 2)
				(curRoom setScript: TrackScript)
			)
		)
	)
)

(instance ZScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global594 2)
				(= seconds 4)
			)
			(1
				(localproc_0e85 local5 40 125)
				(if global207
					(= local5 {IN FRONT})
				else
					(= local5 {NEGATIVE})
				)
				(RadarScript changeState: 0)
				(if (not (cast contains: zoomShip)) (zoomShip init:))
				(zoomShip
					cel: 0
					setLoop: 0
					posn: 185 72
					cycleSpeed: 0
					setCycle: EndLoop self
				)
			)
			(2
				(zoomShip
					cel: 0
					setLoop: 1
					posn: 195 80
					setCycle: EndLoop self
				)
			)
			(3
				(zoomShip cel: 0 setCycle: EndLoop self)
			)
			(4
				(zoomShip cel: 0 setCycle: EndLoop self)
			)
			(5
				(zoomShip dispose:)
				(RedrawCast)
				(boom play:)
				(ShakeScreen 5 3)
				(if
				(or (not (-- shipShieldHealth)) (!= shieldActivated 1))
					(client setScript: BlowShip)
				else
					(localproc_0e85 local5 40 125)
					(= local5 {NEGATIVE})
					(RadarScript changeState: 0)
				)
				(= global594 0)
				(= global175 10)
			)
		)
	)
)

(instance TrackScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(engage setCycle: Forward)
				(trackSound play:)
				(zoomShip
					setLoop: 4
					cel: 0
					posn: 140 70
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(1
				(targShip
					view: 47
					illegalBits: 16384
					setPri: 8
					setStep: 4 4
					cel: 5
					ignoreActors: TRUE
					setCycle: 0
					setMotion: Wander 30
					posn: (zoomShip x?) (zoomShip y?)
					init:
				)
				(zoomShip dispose:)
				(HandsOn)
				(= local3 3)
				(= local0 10)
				(vHair startUpd:)
				(hHair startUpd:)
				(lockSign setCel: 1 init: stopUpd:)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance CenterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(targShip setMotion: MoveTo 160 88 self)
			)
			(1
				(trackSound stop:)
				(lockSound play:)
				(= local3 5)
				(= local1 5)
				(lockSign setCel: 0 forceUpd:)
				(topArrow init: stopUpd:)
				(bottomArrow init: stopUpd:)
				(leftArrow init: stopUpd:)
				(rightArrow init: stopUpd:)
				(targShip
					setStep: 2 2
					setMotion: Wander 10
					illegalBits: -33
				)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance shotScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== local3 5) (targShip setMotion: 0 stopUpd:))
				(if
				(not (= local2 (/ (Abs (- (ego y?) 88)) 4)))
					(= local2 1)
				)
				(if
				(not (= local40 (Abs (/ (- (ego x?) 120) 3))))
					(= local40 1)
				)
				(if
				(not (= local41 (Abs (/ (- 198 (ego x?)) 3))))
					(= local41 1)
				)
				(rShot cel: 255 init:)
				(lShot cel: 255 init:)
				(lockSound stop:)
				(RedrawCast)
				(laser play:)
				(lShot
					setStep: local40 local2
					setCycle: EndLoop
					setMotion: MoveTo (ego x?) (ego y?) self
				)
				(rShot
					setStep: local41 local2
					setCycle: EndLoop
					setMotion: MoveTo (ego x?) (ego y?)
				)
			)
			(1
				(lShot dispose:)
				(rShot dispose:)
				(engage setCel: 0 stopUpd:)
				(if (== local3 5)
					(= local1 -1)
					(targShip dispose:)
					(blast init: setCycle: EndLoop self)
					(lockSound stop:)
					(enemyBoom play:)
				else
					(if (== shipLocation 0) (curRoom newRoom: 20))
					(fire cel: 0 state: 1 draw:)
					(= local39 0)
					(if (== local3 3) (HandsOn))
				)
			)
			(2
				(blast dispose:)
				(lockSign setCel: 2 forceUpd:)
				(fire cel: 0 state: 1 draw:)
				(= local3 2)
				(topArrow dispose:)
				(bottomArrow dispose:)
				(leftArrow dispose:)
				(rightArrow dispose:)
				(ego setMotion: MoveTo 160 88 self)
			)
			(3
				(= local39 0)
				(= local3 1)
				(lockSign dispose:)
				(ego posn: 160 88 stopUpd:)
				(vHair posn: 160 88 stopUpd:)
				(hHair posn: 160 88 stopUpd:)
				(if (== (++ global176) 5)
					(localproc_0e85 local5 40 125)
					(= local5 {NEGATIVE})
					(RadarScript changeState: 0)
					(Print 18 1)
					(curRoom setScript: 0)
					(theGame changeScore: 100)
					(= global218 0)
					(= global175 0)
					(= global594 0)
					(= global167 0)
				else
					(curRoom setScript: ZScript)
				)
			)
		)
	)
)

(instance BlowShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(boom play:)
				(ShakeScreen 20 3)
				(RadarScript changeState: 3)
				(localproc_0e85 local5 40 125)
				(cast eachElementDo: #dispose)
				(cls)
				(curRoom drawPic: 121)
				(if (not shipShieldHealth)
					(Print 18 2)
				else
					(Print 18 3)
				)
				(EgoDead 0 0 4 5)
			)
		)
	)
)

(instance RadarScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_0e6a local5 40 125)
				(Timer setCycle: self 5)
			)
			(1
				(localproc_0e85 local5 40 125)
				(Timer setCycle: self 5)
			)
			(2 (self changeState: 0))
		)
	)
)

(instance Depleted of Script
	(properties)
	
	(method (doit)
		(if
		(and (== (shWarn state?) -1) (!= (self state?) 2))
			(self changeState: 2)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(shWarn play:)
				(depleted init: setCycle: Forward)
			)
			(2
				(depleted setCel: 0 stopUpd:)
				(shWarn dispose:)
			)
		)
	)
)

(instance zoomShip of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self view: 48 ignoreActors: TRUE setPri: 6 illegalBits: 0)
	)
)

(instance frontShield of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: (if (> numColors 4) 46 else 146)
			loop: 6
			posn: 258 119
			setPri: 15
			ignoreActors: TRUE
		)
	)
)

(instance backShield of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: (if (> numColors 4) 46 else 146)
			loop: 7
			posn: 258 127
			setPri: 15
			ignoreActors: TRUE
		)
	)
)

(instance targShip of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 47
			illegalBits: 16384
			setPri: 8
			setStep: 4 4
			cel: 5
			ignoreActors: TRUE
			setCycle: 0
			setMotion: Wander 30
		)
	)
	
	(method (doit)
		(if (!= (self view?) 48)
			(switch (self onControl: 1)
				(2 (self cel: 1))
				(4 (self cel: 2))
				(8 (self cel: 3))
				(16 (self cel: 4))
				(32 (self cel: 5))
				(64 (self cel: 6))
				(128 (self cel: 7))
				(256 (self cel: 8))
				(512 (self cel: 9))
			)
		)
		(super doit:)
	)
)

(instance hHair of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 45
			setLoop: 3
			setCel: 0
			posn: 160 88
			setPri: 9
			ignoreActors: TRUE
			setCycle: 0
		)
	)
	
	(method (doit)
		(if local3 (self y: (ego y?)))
		(super doit:)
	)
)

(instance vHair of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 45
			setLoop: 3
			setCel: 1
			posn: 160 88
			setPri: 9
			ignoreActors: TRUE
			setCycle: 0
		)
	)
	
	(method (doit)
		(if local3 (self x: (ego x?)))
		(super doit:)
	)
)

(instance lockSign of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 45
			loop: 1
			posn: 159 43
			setPri: 15
			ignoreActors: TRUE
		)
	)
)

(instance topArrow of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 45
			setLoop: 2
			setCel: 0
			posn: 160 61
			setPri: 9
			ignoreActors: TRUE
		)
	)
)

(instance bottomArrow of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 45
			setLoop: 2
			setCel: 1
			posn: 160 126
			setPri: 9
			ignoreActors: TRUE
		)
	)
)

(instance leftArrow of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 45
			setLoop: 2
			setCel: 2
			posn: 110 98
			setPri: 9
			ignoreActors: TRUE
		)
	)
)

(instance rightArrow of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 45
			setLoop: 2
			setCel: 3
			setPri: 9
			ignoreActors: TRUE
			posn: 207 98
		)
	)
)

(instance lShot of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 45
			setLoop: 0
			setCel: 0
			setPri: 14
			ignoreActors: TRUE
			posn: 120 88
		)
	)
)

(instance rShot of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 45
			setLoop: 0
			setCel: 0
			setPri: 14
			ignoreActors: TRUE
			posn: 198 88
		)
	)
)

(instance blast of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 55
			setCel: 0
			posn: (ego x?) (ego y?)
			setPri: 9
			cycleSpeed: 1
			ignoreActors: TRUE
		)
	)
)

(instance depleted of Prop
	(properties)
	
	(method (init)
		(super init:)
		(= view (if (> numColors 4) 46 else 146))
		(self
			setLoop: 0
			setCel: 0
			posn: 259 70
			setPri: 15
			ignoreActors: TRUE
		)
	)
)

(instance engage of Prop
	(properties)
	
	(method (init)
		(super init:)
		(= view (if (> numColors 4) 46 else 146))
		(self
			setLoop: 4
			setCel: 0
			x: 67
			y: 70
			setPri: 15
			ignoreActors: TRUE
		)
	)
)

(instance shipShape of PicView
	(properties
		y 127
		x 258
		loop 5
		priority 14
		signal ignrAct
	)
	
	(method (init)
		(= view (if (> numColors 4) 46 else 146))
		(super init:)
	)
)

(instance shWarn of Sound
	(properties
		number 27
		priority 1
	)
)

(instance laser of Sound
	(properties
		number 39
		priority 3
	)
)

(instance trackSound of Sound
	(properties
		number 200
		priority 1
		loop -1
	)
)

(instance lockSound of Sound
	(properties
		number 201
		priority 2
		loop -1
	)
)

(instance boom of Sound
	(properties
		number 33
		priority 1
	)
)

(instance enemyBoom of Sound
	(properties
		number 31
		priority 1
	)
)

(instance HButton of DIcon
	(properties
		state $0001
		nsTop 44
		nsLeft 47
		key 104
		loop 3
	)
	
	(method (init)
		(= view (if (> numColors 4) 46 else 146))
		(super init:)
	)
	
	(method (doit)
		(Print 18 4)
	)
)

(instance exit of DIcon
	(properties
		state $0001
		nsTop 72
		nsLeft 47
		key 111
		loop 9
	)
	
	(method (init)
		(= view (if (> numColors 4) 46 else 146))
		(super init:)
	)
	
	(method (doit)
		(self cel: 1 draw:)
		(curRoom newRoom: 17)
	)
)

(instance FButton of DIcon
	(properties
		state $0001
		nsTop 44
		nsLeft 245
		key 102
		loop 1
	)
	
	(method (init)
		(= view (if (> numColors 4) 46 else 146))
		(super init:)
	)
	
	(method (doit)
		(switch (self cel?)
			(0 (= shieldActivated 1))
			(1 (= shieldActivated 0))
			(2 (return))
		)
		(localproc_0eac)
	)
)

(instance BButton of DIcon
	(properties
		state $0001
		nsTop 72
		nsLeft 245
		key 98
		loop 2
	)
	
	(method (init)
		(= view (if (> numColors 4) 46 else 146))
		(super init:)
	)
	
	(method (doit)
		(switch (self cel?)
			(0 (= shieldActivated 2))
			(1 (= shieldActivated 0))
			(2 (return))
		)
		(localproc_0eac)
	)
)

(instance fire of DIcon
	(properties
		state $0001
		nsTop 135
		nsLeft 137
		key 32
		loop 8
	)
	
	(method (init)
		(= view (if (> numColors 4) 46 else 146))
		(super init:)
	)
	
	(method (doit)
		(if (not local39)
			(= local39 1)
			(self cel: 1 draw:)
			(ego setScript: shotScript)
		)
	)
)
