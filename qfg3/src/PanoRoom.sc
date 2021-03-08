;;; Sierra Script 1.0 - (do not remove this comment)
(script# PANORAMA) ;50
(include game.sh)
(use Main)
(use PolyPath)
(use Timer)
(use Motion)
(use Game)
(use User)
(use System)

(public
	panorama 0
)

(local
	local0
	local1
	local2 =  1
	local3
	egoMoveSpeed
)

(define areaSAVANNA	$0002)
(define areaJUNGLE	$0004)
(define areaLOSTCITY	$1000)

(procedure (InitEncounter where)
	;bring up the Eggo if ego is starving
	(if (and (Random 0 1) (Btst fStarving))
		(= monsterNum 11)
		(return)
	)
	(switch where
		(areaJUNGLE
			(cond 
				;bring up Johari if she's been released, and you haven't been to
				; the Leopardman village
				((and (Btst fJohariReleased) (not (Btst fEnteredLeopardmanVillage)))
					(= monsterNum 6)
				)
				;if you haven't relased Manu from his cage, bring him up
				((== curRoomNum 180)
					(if (not (Btst fManuReleased))
						(= monsterNum 8)
					else
						(switch (Random 0 2)
							(0
								(= monsterNum vApeman)
							)
							(1
								(= monsterNum vDeWorm)
							)
							(else
								(= monsterNum vLeopardmanEnemy)
							)
						)
					)
				)
				((Btst fEnteredLeopardmanVillage)
					;randomly bring up Johari or a monster
					(if (Random 0 1)
						(= monsterNum 6)
					else
						(switch (Random 0 2)
							(0
								(= monsterNum vCobra)
							)
							(1
								(= monsterNum vAnt)
							)
							(else
								(= monsterNum vCroc)
							)
						)
					)
				)
				(Night
					(switch (Random 0 7)
						(0
							(= monsterNum vCobra)
						)
						(1
							(= monsterNum vAnt)
						)
						(2
							(= monsterNum vCroc)
						)
						(3
							(if (== curRoomNum 180)
								(if (Random 0 1)
									(= monsterNum vApeman)
								else
									(= monsterNum vDeWorm)
								)
							else
								(= monsterNum vLeopardmanEnemy)
							)
						)
						(else
							(= monsterNum vLeopardmanEnemy)
						)
					)
				)
				(else
					(switch (Random 0 6)
						(0
							(= monsterNum vCobra)
						)
						(1
							(= monsterNum vAnt)
						)
						(2
							(= monsterNum vCroc)
						)
						(3
							(if (== curRoomNum 180)
								(if (Random 0 1)
									(= monsterNum vApeman)
								else
									(= monsterNum vDeWorm)
								)
							else
								(= monsterNum vLeopardmanEnemy)
							)
						)
						(else 
							;Bring up the orchid if conditions are met
							(if (and (Btst fEnteredGuardianDen) (Btst fNeedStaff) (not (Btst fGotOrchid)))
								(= monsterNum 10)
							else
								(= monsterNum vCobra)
							)
						)
					)
				)
			)
		)
		(areaSAVANNA
			(if (Random 0 3)
				(= monsterNum vCroc)
			else
				(switch (Random 0 1)
					(0
						(= monsterNum vAnt)
					)
					(1 
						(= monsterNum vDinosaur))
				)
			)
			(if (== where areaJUNGLE)
				(Bset fFlag34)
			)
		)
		(areaLOSTCITY
			(switch (Random 0 1)
				(0
					(= monsterNum vDeWorm)
				)
				(1
					(= monsterNum vApeman)
				)
			)
			(Bset fFlag34)
		)
		(16384
			(if
				(and
					(OneOf curRoomNum 150 160)
					(Btst fHoneyBirdHinted)
					(not (Btst fFoundHoneyBird))
				)
				(switch (Random 0 7)
					(0
						(= monsterNum vAnt)
					)
					(1
						(= monsterNum vCroc)
					)
					(else 
						(cond 
							(
								(or
									(and (ego inRect: 171 85 313 115) (== curRoomNum 150))
									(and (ego inRect: 6 62 101 146) (== curRoomNum 160))
								)
								(= honeyBirdRoom curRoomNum)
								(= monsterNum 2)
								(= honeyTreeX (ego x?))
								(= honeyTreeY (ego y?))
							)
							((Random 0 1) (= monsterNum vAnt))
							(else (= monsterNum vCroc))
						)
					)
				)
			else
				(switch (Random 0 1)
					(0 (= monsterNum vAnt))
					(1 (= monsterNum vDinosaur))
				)
			)
		)
	)
)

(procedure (localproc_07df where)
	(if (and (Random 0 1) (Btst fStarving))
		(= monsterNum 11)
		(return)
	)
	(= monsterNum
		(switch (Random 0 50)
			(0 4)
			(1
				(if (== where 16384) 0 else 4)
			)
			(2
				(if (== where 16384) 3 else 4)
			)
			(3
				(if
					(and
						(Btst fHungry)
						(not ((inventory at: iVine) amount?))
						(not (Random 0 9))
					)
					11
				else
					4
				)
			)
			(4 5)
			(5 5)
			(else  999)
		)
	)
)

(class PanoRoom of Room
	(properties
		toX 0
		toY 0
	)
	
	(method (init)
		(walkHandler addToFront: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(self toX: ((User curEvent?) x?))
				(self toY: ((User curEvent?) y?))
				(ego setMotion: PolyPath toX toY curRoom)
			)
			(V_SLEEP
				(Bset fStartedEncounter)
				(= monsterNum 74)
				(= panoEgoX (ego x?))
				(= panoEgoY (ego y?))
				(curRoom newRoom: 0)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (newRoom n)
		(cond 
			((and (not n) (not (Btst fHadMeeting)))
				(if
					(or
						(== (ego onControl: origin) cGREEN)
						(== (ego onControl: origin) cLRED)
					)
					(if (not monsterNum)
						(= monsterNum 999)
					)
					(= n 700)
				else
					(= n 400)
				)
			)
			((Btst fHadMeeting)
				(= n 310)
			)
		)
		(HandsOff)
		(super newRoom: n)
	)
)

(instance panorama of Region
	
	(method (init &tmp temp0 temp1)
		(Bclr fStartedEncounter)
		(= monsterNum 999)
		(= global411 3)
		(if (= temp0 (PalVary PALVARYINFO))
			(= temp1 (/ (- 64 temp0) 10))
			(PalVary PALVARYNEWTIME temp1)
		)
		(switch (ego moveSpeed?)
			(0 (= egoMoveSpeed 3))
			(1 (= egoMoveSpeed 4))
			(2 (= egoMoveSpeed 5))
			(else 
				(= egoMoveSpeed (ego moveSpeed?))
			)
		)
		(ego
			view: 151
			setLoop: 0
			cel: 0
			setCycle: Walk
			cycleSpeed: egoMoveSpeed
			moveSpeed: egoMoveSpeed
			setStep: 1 1
			setScale: 0
			init:
		)
		(= local0
			(switch prevRoomNum
				(400 50)
				(150 50)
				(280 12)
				(410 15)
				(420 25)
				(390 12)
				(600 22)
				(750 10)
				(720 16)
				(else  9)
			)
		)
		(= local0 (/ 60 arcadeDifficulty))
		(if
			(and
				(or
					(== prevRoomNum 400)
					(== prevRoomNum 380)
					(== prevRoomNum 700)
				)
				(not (Btst fGoWithJohari))
			)
			(Timer setTicks: 30 self)
		)
		(super init: &rest)
	)
	
	(method (doit &tmp temp0 temp1 temp2)
		(super doit: &rest)
		(if (!= (ego moveSpeed?) egoMoveSpeed)
			(switch (ego moveSpeed?)
				(0 (= egoMoveSpeed 3))
				(1 (= egoMoveSpeed 4))
				(2 (= egoMoveSpeed 5))
				(else 
					(= egoMoveSpeed (ego moveSpeed?))
				)
			)
			(ego moveSpeed: egoMoveSpeed cycleSpeed: egoMoveSpeed)
		)
		(cond 
			(
				(or
					(!= (ego xLast?) panoEgoX)
					(!= (ego yLast?) panoEgoY)
				)
				(= panoEgoX (ego xLast?))
				(= panoEgoY (ego yLast?))
				(if (not (-- local2))
					(DrawCel 151 8 0 panoEgoX panoEgoY 2)
					(= local2 2)
				)
				(if (Btst fInMainGame)
					(+= Clock 4)
					(if (Btst fNightPaletteDisabled)
						(Bclr fNightPaletteDisabled)
						(if
							(and
								(= temp2 (PalVary PALVARYINFO))
								(!= temp2 0)
								(!= temp2 64)
							)
							(= temp1 (/ (- 64 temp2) 24))
							(PalVary PALVARYNEWTIME temp1)
						)
					)
					(= global411 3)
					(if (and (not (-- local0)) (not (Btst fTravelWithSomeone)))
						(= local0 (- (/ 60 arcadeDifficulty) 10))
						(cond 
							((& (= temp0 (ego onControl: origin)) cLGREEN) 0)
							((not (Random 0 5))
								(= local3 1)
								(curRoom newRoom: 0)
							)
						)
					)
				)
			)
			((not (ego mover?))
				(= global411 1)
				(if (and (not (Btst fNightPaletteDisabled)) (Btst fInMainGame))
					(Bset fNightPaletteDisabled)
					(if (= temp2 (PalVary PALVARYINFO))
						(= temp1 (- 64 temp2))
						(PalVary PALVARYNEWTIME temp1)
					)
				)
			)
		)
	)
	
	(method (dispose &tmp temp0 temp1)
		(if (= temp1 (PalVary PALVARYINFO))
			(= temp0 (- 64 temp1))
			(PalVary PALVARYNEWTIME temp0)
		)
		(= local1
			(- 11 (* 3 arcadeDifficulty))
		)
		(cond 
			((Btst fStartedEncounter) 0)
			((or (>= local1 (Random 0 100)) local3 (InitEncounter (ego onControl: origin))))
			(else
				(localproc_07df (ego onControl: origin))
			)
		)
		(= global411 1)
		(HandsOn)
		(super dispose:)
	)
	
	(method (cue)
		((ScriptID TIME 3) init:)
	)
)
