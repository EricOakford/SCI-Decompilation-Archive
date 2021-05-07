;;; Sierra Script 1.0 - (do not remove this comment)
(script# 51)
(include sci.sh)
(use Main)
(use HighLite)
(use Intrface)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room51 0
)
(synonyms
	(stair upstair)
	(room room passage)
)

(local
	local0
)
(instance glow of HighLite
	(properties)
)

(instance Room51 of Rm
	(properties
		picture 51
	)
	
	(method (init)
		(= horizon 0)
		(= west 55)
		(= global189 51)
		(super init:)
		(self setRegions: 242 setFeatures: trapdoor)
		(addToPics add: trapdoor eachElementDo: #init doit:)
		(if lanternIsLit
			(if (== prevRoomNum 65)
				(= local0 0)
				(HandsOff)
				(ego
					loop: 1
					posn: 222 91
					illegalBits: 0
					setMotion: MoveTo 116 170 self
				)
			else
				(= local0 1)
				(ego loop: 0 y: 170)
			)
			(ego view: 7 xStep: 3 init:)
			(glow deltaX: 8 deltaY: 8 ignoreCast: 1 init:)
			(rat
				view: 151
				setLoop: 3
				setStep: 5 5
				illegalBits: 0
				ignoreActors: 1
				posn: 139 171
				setCycle: Walk
				init:
				setScript: Scurry
			)
		else
			(HandsOff)
			(ego
				view: 49
				loop: 1
				posn: 222 91
				illegalBits: 0
				init:
				setScript: tumble
			)
		)
	)
	
	(method (doit)
		(if (and (FirstEntry) lanternIsLit) (Print 51 0))
		(if (and (not local0) (< (ego x?) 117)) (= local0 1))
		(if (and local0 (& (ego onControl: 1) $0008))
			(HandsOff)
			(ego illegalBits: 0 setMotion: MoveTo 244 80)
		)
		(if (& (ego onControl: 1) $0002)
			(ego illegalBits: -32768)
			(curRoom newRoom: 65)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 214)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(if (== (event type?) evSAID)
			(cond 
				((Said '*/dinosaur') (Print 51 1))
				((Said '*/bone') (Print 51 2))
				((Said 'examine>')
					(cond 
						((Said '[<around,at][/room]') (Print 51 0))
						((Said '/stair') (Print 51 3))
						((Said '/boulder') (Print 51 4))
					)
				)
				((Said 'open/trapdoor') (Print 51 5))
				((Said 'close/trapdoor') (Print 51 6))
				((Said 'climb/stair') (Print 51 7))
			)
		)
	)
	
	(method (cue)
		(HandsOn)
		(ego illegalBits: -32768)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance tumble of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Falling priority: 5 play:)
				(ego setCycle: End self)
			)
			(1
				(ego
					setLoop: 3
					setCycle: Fwd
					xStep: 8
					yStep: 8
					setMotion: MoveTo 100 158 self
				)
			)
			(2
				(ego posn: 103 171 setLoop: 5 cel: 0 setCycle: End self)
				(ShakeScreen 5 5)
			)
			(3
				(= cIcon 49)
				(= deathLoop 5)
				(= deathCel 4)
				(EgoDead 51 8)
			)
		)
	)
)

(instance Scurry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 5)))
			(1
				(rat setMotion: MoveTo -10 169 self)
				(ratNoise play:)
			)
			(2
				(rat dispose:)
				(client setScript: 0)
			)
		)
	)
)

(instance rat of Act
	(properties)
	
	(method (handleEvent event)
		(cond 
			((Said '/mouse>')
				(cond 
					((Said 'examine') (if (rat mover?) (Print 51 9) else (DontSee)))
					((Said 'get,capture') (if (rat mover?) (Print 51 10) else (DontSee)))
					((Said 'kill') (if (rat mover?) (Print 51 11) else (DontSee)))
				)
			)
			((and (rat mover?) (MousedOn self event 3)) (event claimed: 1) (Print 51 9))
		)
	)
)

(instance Falling of Sound
	(properties
		number 70
	)
)

(instance ratNoise of Sound
	(properties
		number 58
	)
)

(instance trapdoor of RPicView
	(properties
		y 55
		x 239
		view 151
		priority 3
	)
	
	(method (handleEvent event)
		(if
			(or
				(MousedOn self event 3)
				(Said 'examine/trapdoor')
				(Said 'examine<up')
			)
			(event claimed: 1)
			(Print 51 12)
		)
	)
)
