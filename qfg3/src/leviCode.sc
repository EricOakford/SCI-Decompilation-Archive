;;; Sierra Script 1.0 - (do not remove this comment)
(script# LEVITATION) ;31
(include game.sh) (include "31.shm")
(use Main)
(use EgoDead)
(use PolyPath)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	leviCode 0
	sLevitate 1
)

(local
	toX
	toY
	theTheEgoX =  100
	i
	dirAngle = [135 225 45 315]
	dirLoop = [0 1 0 2 0 1 2 3]
	holdingStaff
	egoLoop
	local18
	newTime
	sparkles
	local21
	[local22 10]
)
(instance leviCode of Code
	
	(method (init toPosn)
		(HandsOff)
		(ego setMotion: 0)
		(if argc
			(= toX [toPosn 0])
			(= toY [toPosn 1])
		else
			(= toX (ego x?))
			(= toY (ego y?))
			(sLevitate start: 2)
		)
		(if (> argc 2)
			(= theTheEgoX [toPosn 2])
		)
		(if (> argc 3)
			(= i (Min [toPosn 3] 3))
		)
		((ScriptID curRoomNum) setScript: sLevitate)
	)
)

(instance sLevitate of Script
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(if (< state 9)
			(if (>= state 3)
				(cond 
					((ego mover?)
						(if (== state 5)
							(= local21 0)
						else
							(= local21 (+ (- toY ((ego mover?) y?)) (ego z?)))
						)
						(ego y: toY x: toX setMotion: 0)
					)
					((< (ego z?) theTheEgoX)
						(cond 
							((< local21 (ego z?)) (ego z: (- (ego z?) 1)))
							((> local21 (ego z?)) (ego z: (+ (ego z?) 1)))
						)
					)
					((< local21 theTheEgoX) (ego z: (- (ego z?) 1)))
				)
			)
			(if (IsObject sparkles)
				(sparkles z: (ego z?))
			)
			(if (== state 4)
				(if
					(and
						(not
							(if (and (> (ego view?) 17) (< (ego view?) 21)))
						)
						(> (Abs (- gameTime newTime)) 50)
					)
					(= newTime gameTime)
					(if (not (ego useMana: 1))
						(self cue:)
					)
				)
				(if (<= (ego z?) 0)
					(= state 7)
					(ego z: 0)
					(self cue:)
				)
			)
			(if (<= (ego z?) 0)
				(if (== state 5) (self cue:))
				(ego z: 0)
			)
		)
	)
	
	(method (dispose)
		(= useObstacles TRUE)
		(ego z: 0)
		(= holdingStaff FALSE)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (> (ego view?) 17) (< (ego view?) 21))
					(if (< (ego view?) 21)
						(= holdingStaff TRUE)
					)
				)
				(ego setMotion: PolyPath toX toY self)
			)
			(1
				(cond 
					(holdingStaff
						((ego looper?) dispose:)
						(ego looper: 0)
						(Face ego (+ (ego x?) 5) (+ (ego y?) 5) self)
					)
					(i
						(ego setHeading: [dirAngle i] self)
					)
					(else
						(self cue:)
					)
				)
			)
			(2
				(= useObstacles FALSE)
				(= egoLoop (ego loop?))
				(if holdingStaff
					(ego
						view: 19
						setLoop: 5
						setCel: 0
						setCycle: CycleTo 4 1 self
						xStep: 0
						setPri: (ego priority?)
					)
				else
					(ego
						view: 17
						setLoop: (if i else [dirLoop (ego loop?)])
						setCel: 0
						setCycle: EndLoop self
						xStep: 0
						setPri: (ego priority?)
					)
				)
				(globalSound number: 281 setLoop: 1 play:)
			)
			(3
				(if holdingStaff
					(ego
						loop: 6
						setCel: 0
						setMotion: MoveTo (ego x?) (- (ego y?) 10)
					)
				else
					(ego
						setCel: 255
						setMotion: MoveTo (ego x?) (- (ego y?) 10)
					)
				)
				((= sparkles (Prop new:))
					view: 17
					loop: 4
					x: (ego x?)
					y: (+ (ego y?) 1)
					setCycle: Forward
					setScale:
					scaleX: (ego scaleX?)
					scaleY: (ego scaleY?)
					priority: (ego priority?)
					signal: ignrAct
					init:
				)
				(= cycles 15)
			)
			(4
				(User canControl: TRUE canInput: TRUE)
				(theIconBar enable: ICON_WALK advanceCurIcon:)
			)
			(5
				(HandsOff)
				(= holdingStaff FALSE)
				(sparkles dispose:)
				(= sparkles 0)
				(messager say: N_LEVITATE V_DOIT C_OUT_OF_MANA 1 0 LEVITATION)
				(= register (ego z?))
				(ego
					view: 6
					setLoop: (if (Random 0 1) 0 else 4)
					cel: 0
					setCycle: CycleTo 2 1
					setMotion: MoveTo (ego x?) (+ toY 10)
				)
			)
			(6
				(ShakeScreen shakeSRight)
				(ego setCycle: EndLoop self)
				(globalSound number: 901 play:)
			)
			(7
				(if (ego takeDamage: (/ register 5))
					(ego loop: (+ (ego loop?) 2) cel: 0 setCycle: EndLoop self)
				else
					(EgoDead C_DEATH_FALL LEVITATION)
				)
			)
			(8
				(if (IsObject sparkles)
					(sparkles dispose:)
					(= sparkles 0)
					(if holdingStaff
						(ego loop: 5 cel: 4 setCycle: BegLoop self)
					else
						(ego setCycle: BegLoop self)
					)
				else
					(ego normalize: egoLoop)
					(HandsOn)
					(self dispose:)
				)
			)
			(9
				(if holdingStaff
					(ego view: 20 loop: 2 cel: 4)
				else
					(ego normalize: egoLoop)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
