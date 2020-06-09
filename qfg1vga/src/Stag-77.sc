;;; Sierra Script 1.0 - (do not remove this comment)
(script# 77)
(include game.sh) (include "77.shm")
(use Main)
(use CastFlame)
(use CastDagger)
(use CastRock)
(use Target)
(use Procs)
(use PolyPath)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm77 0
)

(local
	[theSeconds 4]
	local4
	local5
	local6
	local7
)
(instance rm77 of Room
	(properties
		noun N_ROOM
		picture 702
		style DISSOLVE
		horizon 57
	)
	
	(method (init)
		(self setRegions: FOREST)
		(Load RES_VIEW 700)
		(if
		(and (not Night) (!= prevRoomNum 76) (Btst STAG_PRESENT))
			(Load RES_VIEW 78)
		)
		(super init:)
		(StatusLine enable:)
		(NormalEgo)
		(cond 
			((and (not (Btst fMetDryad)) (not monsterNum)) (Bset STAG_PRESENT) (= monsterNum 0))
			(
			(and (Btst DRYAD_AGREED_HELP) (not monsterNum) (not (Btst STAG_PRESENT)))
			(switch (Random 0 1)
				(0 (Bclr STAG_PRESENT))
				(1 (Bset STAG_PRESENT))
			))
		)
		(if
		(and (not Night) (!= prevRoomNum 76) (Btst STAG_PRESENT))
			(= [theSeconds 0] (Random 74 165))
			(= [theSeconds 1] (Random 120 160))
			(= [theSeconds 2] (Random 0 1))
			(= [theSeconds 3] (Random 5 30))
			(stag view: 78 x: 92 y: 142 setScript: stagScript init:)
		else
			(Bclr STAG_PRESENT)
		)
		(= local6 0)
		(= local5 (Btst STAG_PRESENT))
		(southBush addToPic:)
		(if
		(and Night (== prevRoomNum 170) (Btst fFaeryAttention))
			(HandsOff)
			(ego setScript: fairyWalkIn)
			(Load RES_SCRIPT 295)
			(Bclr fFaeryAttention)
			(self setScript: (ScriptID 295 0))
		)
	)
	
	(method (dispose)
		(Bset fBeenIn77)
		(super dispose:)
	)
)

(instance southBush of View
	(properties
		x 58
		y 167
		noun N_SOUTHBUSH
		view 700
		loop 3
		priority 15
	)
)

(instance stag of TargActor
	(properties
		noun N_STAG
		view 78
	)
	
	(method (init)
		(HandsOff)
		(ego setScript: stagWalkIn)
		(super init:)
	)
	
	(method (doit)
		(= local4 (ego distanceTo: self))
		(if (!= script stagBolts)
			(cond 
				([theSeconds 2] (if (< local4 95) (self setScript: stagBolts)))
				((< local4 75) (self setScript: stagBolts))
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ROCK (CastRock self))
			(V_DAGGER
				(CastDagger self)
			)
			(V_FLAME
				(CastFlame self)
			)
			(V_SWORD (self setScript: stagBolts))
			(else 
				(switch local6
					(0 (messager say: N_STAG V_LOOK C_STAGFORAGING))
					(5 (messager say: N_STAG V_LOOK C_STAGLEAP))
					(else  (messager say: N_STAG V_LOOK))
				)
			)
		)
	)
	
	(method (getHurt)
		(= missedDaggers (+ missedDaggers hitDaggers))
		(= hitDaggers 0)
		(Bset STAG_HURT)
		(if
		(and (!= script stagBolts) (!= script stagHurt))
			(self setScript: stagHurt)
		)
	)
)

(instance stagWalkIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 90))
			(1
				(HandsOff)
				(switch prevRoomNum
					(70
						(ego setMotion: PolyPath 160 70 self)
					)
					(78
						(ego setMotion: PolyPath 260 155 self)
					)
					(else 
						(ego setMotion: PolyPath 80 100 self)
					)
				)
			)
			(2
				(HandsOff)
				(switch prevRoomNum
					(70 (ego loop: 2))
					(78 (ego loop: 1))
					(else  (ego loop: 0))
				)
				(= ticks 30)
			)
			(3
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance stagScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ChangeGait MOVE_WALK 0)
				(User canControl: FALSE)
				(if [theSeconds 3]
					(= local6 0)
					(if [theSeconds 2]
						(stag loop: 6 cycleSpeed: 12 moveSpeed: 12 setCycle: Forward)
					else
						(stag loop: 7 cycleSpeed: 12 moveSpeed: 12 setCycle: Forward)
					)
					(= seconds [theSeconds 3])
				else
					(self cue:)
				)
			)
			(1
				(if [theSeconds 3]
					(stag setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(2
				(if [theSeconds 3]
					(= local6 2)
					(if [theSeconds 2]
						(stag
							loop: 4
							cel: 7
							cycleSpeed: 6
							moveSpeed: 6
							setCycle: BegLoop self
						)
					else
						(stag
							loop: 5
							cel: 7
							cycleSpeed: 6
							moveSpeed: 6
							setCycle: BegLoop self
						)
					)
				else
					(self cue:)
				)
			)
			(3
				(if [theSeconds 2]
					(= local6 4)
					(stag
						loop: 2
						cel: 0
						cycleSpeed: 6
						moveSpeed: 6
						xStep: 5
						yStep: 3
						setCycle: EndLoop self
					)
				else
					(self cue:)
				)
			)
			(4
				(User canControl: TRUE)
				(= local6 6)
				(stag
					loop: 1
					cel: 0
					setCycle: Forward
					setMotion: PolyPath -40 (stag y?) self
				)
			)
			(5
				(stag posn: -250 (stag y?))
				(self cue:)
			)
			(6
				(User canControl: TRUE)
				(stag dispose:)
			)
		)
	)
)

(instance stagHurt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_STAG V_FLAME 0 0 self)
			)
			(1
				(client setScript: stagBolts)
			)
		)
	)
)

(instance stagBolts of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (== (ego script?) stagWalkIn))
					(HandsOff)
					(ego setCycle: 0 setMotion: 0)
				)
				(if (== local6 0)
					(= local6 1)
					(stag setCycle: EndLoop)
				else
					(self cue:)
				)
				(messager say: N_STAG V_LOOK 0 0 self)
			)
			(1
				(stag cycleSpeed: 4 moveSpeed: 4)
				(cond 
					((== local6 1)
						(= local6 3)
						(if [theSeconds 2]
							(stag loop: 4 cel: 7 setCycle: BegLoop self)
						else
							(stag loop: 5 cel: 7 setCycle: BegLoop self)
						)
					)
					((== local6 2) (= local6 3) (stag setCycle: BegLoop self))
					(else (self cue:))
				)
			)
			(2
				(cond 
					([theSeconds 2]
						(cond 
							((== local6 3)
								(= local6 5)
								(stag loop: 2 cel: 0 xStep: 5 setCycle: EndLoop self)
							)
							((== local6 4) (= local6 5) (stag setCycle: EndLoop self))
							(else (self cue:))
						)
					)
					((== local6 3) (= local6 5) (self cue:))
				)
			)
			(3
				(if (== local6 5)
					(stag
						setLoop: 9
						cel: 0
						setStep: 2 2
						setCycle: CycleTo 2 1 self
					)
				else
					(self cue:)
				)
			)
			(4
				(User canControl: TRUE)
				(if (== local6 5)
					(stag
						setStep: 12 9
						setCycle: Forward
						setMotion: MoveTo -50 (stag y?) self
					)
				else
					(self cue:)
				)
			)
			(5
				(if (< (stag x?) -30)
					(self cue:)
				else
					(stag
						setStep: 12 9
						setLoop: 9
						setCycle: Forward
						setMotion: MoveTo -50 (stag y?) self
					)
				)
			)
			(6
				(stag dispose:)
				(NormalEgo)
				(HandsOn)
				(if (== (ego script?) (ScriptID 804 1))
					((ScriptID 804 1) changeState: 4)
				else
					(ego setMotion: PolyPath 160 140 setScript: 0)
				)
				(self dispose:)
			)
		)
	)
)

(instance fairyWalkIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 90))
			(1
				(HandsOff)
				(ego setMotion: PolyPath 160 110 self)
			)
			(2
				(ego loop: 3)
				(self dispose:)
			)
		)
	)
)
