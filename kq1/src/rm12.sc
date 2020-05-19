;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Feature)
(use LoadMany)
(use Reverse)
(use RFeature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm12 0
)

(procedure (localproc_187c)
	(ego
		view: 212
		ignoreActors:
		posn: 183 115
		loop: 3
		cel: 0
		setPri: 9
	)
	(knifehand
		view: 212
		ignoreActors:
		posn: 193 104
		setCycle: Forward
		loop: 4
		cel: 0
		setPri: 10
		init:
	)
)

(procedure (localproc_18d2)
	(knifehand cel: 0 stopUpd:)
	(knifehand dispose:)
)

(procedure (TryOtherSide)
	(Print 12 58)
)

(procedure (CantWhileInvisible)
	(Print 12 59)
)

(procedure (BucketAtBottom)
	(Print 12 60)
)

(procedure (localproc_1906)
	(HandsOn)
	(NormalEgo)
	(ego loop: 2 posn: 184 135)
)

(procedure (localproc_1921)
	(HandsOff)
	(ego
		view: 212
		ignoreActors:
		posn: 210 140
		setCycle: Forward
		loop: 1
		cel: 0
		cycleSpeed: 1
		priority: 12
	)
)

(procedure (CantFromHere)
	(Print 12 61)
)

(procedure (RightSide)
	(ego inRect: 160 115 200 138)
)

(procedure (WrongSide)
	(ego inRect: 140 150 218 162)
)

(procedure (AlreadyCutRope)
	(Print 12 49)
)

(instance rm12 of Room
	(properties
		picture 12
		horizon 74
		north 21
		east 13
		south 5
		west 11
	)
	
	(method (init)
		(LoadMany VIEW 212 61 (if (Btst fLittleEgo) 17 else 15))
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego posn: (proc0_17 275 (ego x?) 163) (+ horizon 2))
			)
			(south
				(ego posn: (proc0_17 319 (ego x?) 90) 188)
			)
			(west
				(ego posn: 3 (proc0_17 187 (ego y?) 117))
			)
			(else 
				(ego posn: 317 (proc0_17 187 (ego y?) 118))
			)
		)
		(ego init:)
		(NormalEgo)
		(addToPics add: vine eachElementDo: #init doit:)
		(crank setPri: 10 ignoreActors: init: stopUpd:)
		(if
			(not
				(if (or (Btst fRopeLowered) (Btst fBucketLowered) (ego has: iWaterBucket))
				else
					(Btst fCutRope)
				)
			)
			(bucket
				view: 212
				posn: 179 122
				illegalBits: 0
				setPri: 10
				setCel: 1
				setLoop: 5
				ignoreActors:
				init:
				stopUpd:
			)
		)
		(cond 
			((cast contains: bucket)
				(baranrope loop: 0 cel: 0)
			)
			((or (Btst fBucketLowered) (Btst fRopeLowered))
				(baranrope loop: 0 cel: 14)
			)
			((Btst fCutRope)
				(baranrope loop: 5 cel: 0)
			)
		)
		(baranrope setPri: 10 ignoreActors: init: stopUpd:)
		(if (and (Btst fClimbingRope) (== prevRoomNum 49))
			(curRoom setScript: climbOutaWell)
		)
		(tree1 init:)
		(tree2 init:)
		(tree3 init:)
		(tree4 init:)
		(tree5 init:)
		(bush init:)
		(bush2 init:)
		(wellBush init:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'take,get,take,pick,uncoil/vine')
				(Print 12 0)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,clearing]')
						(Print 12 1)
					)
					((Said '/blossom')
						(Print 12 2)
					)
					((Said '<in,down/well')
						(if (ego inRect: 163 131 199 161)
							(Print 12 3)
						else
							(Print 12 4)
						)
					)
					((Said '<down')
						(if (ego inRect: 163 131 199 161)
							(Print 12 3)
						else
							(Print 12 5)
						)
					)
					((Said '/well')
						(Print 12 6)
					)
					((Said 'look,check/grass,grass')
						(Print 12 5)
					)
					((Said '<in/bucket')
						(if (Btst fBucketLowered)
							(Print 12 7)
						else
							(event claimed: FALSE)
						)
					)
					((and (Said '/water') (not (Btst fWaterInBucket)))
						(if (ego inRect: 163 131 199 161)
							(Print 12 8)
						else
							(Print 12 9)
						)
					)
				)
			)
			((Said 'get,take/water')
				(cond 
					((not (ego has: iWaterBucket))
						(Print 12 10)
					)
					((not (Btst fWaterInBucket))
						(Print 12 11)
					)
					(else
						(Print 12 12)
					)
				)
			)
			(
				(or
					(Said '(climb,scale[<down,in]),(go<down)[/rope,well]')
					(Said 'enter/well')
					(Said 'get,take<in,in/well')
					(Said 'slide/rope')
				)
				(cond 
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((Btst fGoatFollows)
						(Print 12 13)
					)
					;duplicate case
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((WrongSide)
						(TryOtherSide)
					)
					((RightSide)
						(event claimed: FALSE)
						(cond 
							((or (Btst fBucketLowered) (Btst fRopeLowered))
								(event claimed: TRUE)
								(curRoom setScript: climbRope)
							)
							((Said '/well')
								(Print 12 14)
							)
							((Btst fCutRope)
								(event claimed: TRUE)
								(Print 12 15)
							)
							(else
								(event claimed: TRUE)
								(Print 12 16)
							)
						)
					)
					(else
						(CantReach)
					)
				)
			)
			(
				(or
					(Said 'climb,scale[<in]/bucket')
					(Said 'sit[<in]/bucket')
					(Said 'enter/bucket')
					(Said 'get,take<in,in/bucket')
				)
				(cond 
					((Btst fGoatFollows)
						(Print 12 17)
					)
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((WrongSide)
						(TryOtherSide)
					)
					((RightSide)
						(cond 
							((ego has: iWaterBucket)
								(Print 12 18)
							)
							((or (Btst fBucketLowered) (Btst fCutRope))
								(BucketAtBottom)
							)
							((Btst fBucketFloats)
								(Print 12 19)
							)
							(else
								(curRoom setScript: intoBucket)
							)
						)
					)
					(else
						(CantReach)
					)
				)
			)
			((Said 'jump/bucket')
				(cond 
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((WrongSide)
						(TryOtherSide)
					)
					((ego has: iWaterBucket)
						(Print 12 20)
					)
					((RightSide)
						(if (or (Btst fBucketLowered) (Btst fBucketFloats))
							(Print 12 21)
							(curRoom setScript: jumpsInWell)
						else
							(Print 12 22)
							(curRoom setScript: jumpsInWell)
						)
					)
					(else (CantReach))
				)
			)
			((Said 'jump')
				(cond 
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((WrongSide)
						(TryOtherSide)
					)
					((RightSide)
						(curRoom setScript: jumpsInWell)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			((Said 'untie/rope')
				(Print 12 23)
			)
			((Said 'untie,get,take/bucket')
				(cond 
					((ego has: iWaterBucket)
						(Print 12 24)
					)
					((Btst fCutRope)
						(BucketAtBottom)
					)
					((Btst fBucketFloats)
						(Print 12 25)
					)
					((ego inRect: 163 131 199 161)
						(Print 12 26)
					)
					(else
						(CantReach)
					)
				)
			)
			(
				(or
					(Said 'lower/bucket')
					(and (cast contains: bucket) (Said 'lower/rope'))
				)
				(cond 
					((ego has: iWaterBucket)
						(Print 12 27)
					)
					((Btst fCutRope)
						(AlreadyCutRope)
					)
					((Btst fBucketLowered)
						(BucketAtBottom)
					)
					((Btst fBucketFloats)
						(Print 12 28)
					)
					(script
						(script doit:)
						(CantDo)
					)
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((ego inRect: 190 115 230 138)
						(curRoom setScript: crankDown)
					)
					(else
						(CantFromHere)
					)
				)
			)
			((Said 'lower/rope')
				(cond 
					((Btst fCutRope)
						(AlreadyCutRope)
					)
					((Btst fRopeLowered)
						(Print 12 29)
					)
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((ego inRect: 190 115 230 138)
						(curRoom setScript: crankDown)
					)
					(else
						(CantFromHere)
					)
				)
			)
			((or (Said 'raise/bucket') (and (not (Btst fBucketFloats)) (Btst fBucketLowered) (Said 'raise/rope')))
				(if (ego inRect: 190 115 230 138)
					(cond 
						((Btst fInvisible)
							(CantWhileInvisible)
						)
						((ego has: iWaterBucket)
							(Print 12 30)
						)
						((Btst fCutRope)
							(AlreadyCutRope)
						)
						((Btst fBucketFloats)
							(Print 12 31)
						)
						((not (Btst fBucketLowered))
							(Print 12 32)
						)
						(else
							(curRoom setScript: crankUp)
						)
					)
				else
					(CantFromHere)
				)
			)
			((Said 'raise/rope')
				(cond 
					((Btst fCutRope)
						(AlreadyCutRope)
					)
					((not (Btst fRopeLowered))
						(Print 12 33)
					)
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					(else
						(curRoom setScript: crankUp)
					)
				)
			)
			((Said 'turn/crank,handle')
				(cond 
					((Btst fCutRope)
						(AlreadyCutRope)
					)
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((not (ego inRect: 190 115 230 138))
						(CantFromHere)
					)
					((Btst fRopeLowered)
						(curRoom setScript: crankUp)
					)
					((not (Btst fRopeLowered))
						(curRoom setScript: crankDown)
					)
				)
			)
			((Said 'tie,drop,drop/bucket')
				(if (ego has: iWaterBucket)
					(Print 12 34)
				else
					(DontHave)
				)
			)
			((or (Said 'cut/rope') (Said 'cut<off/bucket'))
				(cond 
					((not (ego has: iDagger))
						(Print 12 35)
					)
					((Btst fInvisible)
						(CantWhileInvisible)
					)
					((WrongSide)
						(TryOtherSide)
					)
					((RightSide)
						(cond 
							((Btst fBucketLowered)
								(curRoom setScript: cutLowered)
							)
							((Btst fRopeLowered)
								(curRoom setScript: cutLowered)
							)
							((Btst fCutRope)
								(Print 12 36)
							)
							((Btst fBucketFloats)
								(Print 12 37)
							)
							((not (ego has: iWaterBucket))
								(curRoom setScript: cutOffBucket)
							)
							(else
								(Print 12 38)
							)
						)
					)
					(else
						(CantReach)
					)
				)
			)
		)
	)
)

(instance rope of Prop
	(properties
		x 179
		y 125
		view 212
		loop 8
		cycleSpeed 1
	)
)

(instance bucket of Actor
	(properties)
	
	(method (init)
		(= xLast x)
		(= yLast y)
		(= signal (& signal $7fff))
		(if (not (cast contains: self))
			(= lsRight (= lsBottom (= lsLeft (= lsTop 0))))
			(= signal (& signal $ff77))
		)
		(BaseSetter self)
		(cast addToFront: self)
		(= description name)
		(Feature init:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check<in/bucket')
				(cond 
					((ego has: iWaterBucket)
						(if (Btst fWaterInBucket)
							(Print 12 39)
						else
							(Print 12 40)
						)
					)
					((ego inRect: 163 131 199 161)
						(Print 12 41)
					)
					(else
						(Print 12 42)
					)
				)
			)
			((or (Said 'look,check/bucket') (MousedOn bucket event shiftDown))
				(if (ego has: iWaterBucket)
					(event claimed: FALSE)
				else
					(event claimed: TRUE)
					(self doVerb: verbLook)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(cond 
					((Btst fBucketLowered)
						(Print 12 43)
					)
					((ego inRect: 163 131 199 161)
						(if (Btst fWaterInBucket)
							(Print 12 44)
						else
							(Print 12 45)
						)
					)
					(else
						(Print 12 46)
					)
				)
			)
		)
	)
)

(instance crank of Prop
	(properties
		x 212
		y 102
		view 212
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/crank,handle')
				(self doVerb: verbLook)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 12 47)
			)
		)
	)
)

(instance knifehand of Prop
	(properties
		x 193
		y 104
		view 212
		loop 9
	)
)

(instance baranrope of Prop
	(properties
		x 178
		y 94
		view 212
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check/rope')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(cond 
					((Btst fRopeLowered)
						(Print 12 48)
					)
					((Btst fCutRope)
						(Print 12 49)
					)
					(else
						(Print 12 50)
					)
				)
			)
		)
	)
)

(instance vine of RPicView
	(properties
		x 180
		y 157
		description {vine}
		sightAngle 180
		closeRangeDist 500
		longRangeDist 500
		view 212
		loop 6
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/vine')
				(self doVerb: verbLook)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 12 51)
			)
		)
	)
)

(instance jumpsInWell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 183 135 self)
			)
			(1
				(ego
					view: (if (Btst fLittleEgo) 17 else 15)
					setLoop: 0
					cel: 0
					setStep: 1 5
					setMotion: MoveTo 177 119
					setCycle: EndLoop self
				)
			)
			(2
				(baranrope setPri: 11)
				(bucket setPri: 11)
				(ego
					setCel: 6
					setPri: 10
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 181 184 self
				)
			)
			(3
				(Bclr fGoatFollows)
				(FadeBackgroundMusic)
				(curRoom newRoom: 49)
			)
		)
	)
)

(instance intoBucket of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 183 135 self)
			)
			(1
				(ego
					view: 212
					loop: 7
					cel: 0
					setPri: 10
					illegalBits: 0
					ignoreActors:
					posn: 179 122
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(bucket dispose:)
			)
			(2
				((ScriptID 0 21) number: 24 loop: -1 init: play:)
				(ego
					setLoop: 5
					setCel: 2
					setStep: 0 1
					setMotion: MoveTo 179 155
				)
				(crank setCycle: Forward)
				(baranrope setCycle: EndLoop self)
			)
			(3
				(baranrope stopUpd:)
				(bucket stopUpd:)
				(rope init: setPri: 10 setCycle: Forward)
				(= cycles 25)
			)
			(4
				(rope dispose:)
				((ScriptID 0 21) stop:)
				(Print 12 52)
				(Bset fEgoInBucket)
				(Bset fRopeLowered)
				(HandsOn)
				(FadeBackgroundMusic)
				(curRoom newRoom: 49)
			)
		)
	)
)

(instance crankUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (== (ego x?) 215) (== (ego y?) 135))
					(self changeState: 2)
				else
					(ego setMotion: MoveTo 215 132 self)
				)
			)
			(1
				(ego setMotion: MoveTo 215 135 self)
			)
			(2
				((ScriptID 0 21) number: 24 loop: -1 init: play:)
				(localproc_1921)
				(crank hide:)
				(= cycles 5)
			)
			(3
				(baranrope stopUpd:)
				(bucket stopUpd:)
				(rope init: setPri: 11 setCycle: Reverse)
				(= cycles 25)
			)
			(4
				(rope dispose:)
				(localproc_1921)
				(if (and (not (ego has: iWaterBucket)) (not (Btst fBucketFloats)))
					(bucket
						view: 212
						init:
						setStep: 0 1
						setPri: 10
						illegalBits: 0
						ignoreActors: 1
						setCycle: 0
						setLoop: 5
						setCel: 1
						posn: 179 149
						setMotion: MoveTo 179 122
					)
				)
				(baranrope cycleSpeed: 1 ignoreActors: setCycle: BegLoop)
				(= cycles 29)
			)
			(5
				(baranrope stopUpd:)
				(bucket stopUpd:)
				((ScriptID 0 21) stop:)
				(Bclr fBucketLowered)
				(Bclr fRopeLowered)
				(crank show:)
				(HandsOn)
				(NormalEgo)
				(ego loop: 2)
				(self dispose:)
			)
		)
	)
)

(instance crankDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (== (ego x?) 215) (== (ego y?) 135))
					(self changeState: 2)
				else
					(ego setMotion: MoveTo 215 132 self)
				)
			)
			(1
				(ego setMotion: MoveTo 215 135 self)
			)
			(2
				(localproc_1921)
				(crank hide:)
				((ScriptID 0 21) number: 24 loop: -1 init: play:)
				(if (cast contains: bucket)
					(bucket setStep: 0 1 startUpd: setMotion: MoveTo 179 152)
				)
				(baranrope cycleSpeed: 1 ignoreActors: setCycle: EndLoop self)
			)
			(3
				(baranrope stopUpd:)
				(bucket stopUpd:)
				(rope init: setPri: 11 setCycle: Forward)
				(= cycles 25)
			)
			(4
				((ScriptID 0 21) stop:)
				(rope dispose:)
				(crank show:)
				(HandsOn)
				(NormalEgo)
				(ego loop: 2)
				(if (cast contains: bucket)
					(Print 12 53)
					(Bset fRopeLowered)
					(Bset fBucketLowered)
					(bucket dispose:)
				else
					(Print 12 54)
					(Bset fRopeLowered)
				)
				(self dispose:)
			)
		)
	)
)

(instance cutLowered of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 183 135 self)
			)
			(1
				(localproc_187c)
				(= cycles 12)
			)
			(2
				(localproc_18d2)
				(baranrope loop: 5 setCel: 0 stopUpd:)
				(bucket
					view: 212
					init:
					setLoop: 5
					setCel: 3
					posn: 179 122
					setStep: 1 3
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 179 148 self
				)
			)
			(3
				(bucket stopUpd:)
				(cond 
					((and (Btst fBucketLowered) (not (Btst fGotBucketFromWell)))
						(Print 12 55)
					)
					((Btst fRopeLowered)
						(Print 12 56)
					)
				)
				(Bset fCutRope)
				(Bclr fBucketLowered)
				(Bclr fRopeLowered)
				(bucket dispose:)
				(localproc_1906)
				(self dispose:)
			)
		)
	)
)

(instance cutOffBucket of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 183 135 self)
			)
			(1
				(localproc_187c)
				(= cycles 12)
			)
			(2
				(localproc_18d2)
				(bucket dispose:)
				(RedrawCast)
				((ScriptID 0 21) number: 105 loop: 1 init: play:)
				(Print 12 57 #at -1 110)
				(SolvePuzzle fGotBucket 2)
				(ego get: iWaterBucket)
				(localproc_1906)
				(self dispose:)
			)
		)
	)
)

(instance climbRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 188 135 self)
			)
			(1
				(ego setHeading: 270)
				(self cue:)
			)
			(2
				(ego
					view: 61
					loop: 1
					cel: 0
					posn: 188 117
					setPri: 10
					setCycle: EndLoop self
				)
			)
			(3
				(ego
					loop: 0
					posn: 179 95
					setCycle: Forward
					setMotion: MoveTo 179 133 self
				)
			)
			(4
				(Bset fClimbingRope)
				(curRoom newRoom: 49)
			)
		)
	)
)

(instance climbOutaWell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 61
					loop: 0
					cel: 0
					setPri: 10
					posn: 179 113
					setMotion: MoveTo 179 95 self
				)
			)
			(1
				(ego loop: 1 cel: 5 posn: 188 117 setCycle: BegLoop self)
			)
			(2
				(NormalEgo)
				(ego posn: 188 135 loop: 2)
				(Bclr fClimbingRope)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance tree1 of NewFeature
	(properties
		x 81
		y 37
		noun '/ceder'
		nsTop -1
		nsBottom 76
		nsRight 162
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Beautiful shade trees abound in this part of Daventry.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 11
		y 96
		noun '/ceder'
		nsTop 76
		nsLeft 3
		nsBottom 116
		nsRight 20
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Beautiful shade trees abound in this part of Daventry.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 81
		y 113
		noun '/ceder'
		nsTop 76
		nsLeft 64
		nsBottom 151
		nsRight 98
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Beautiful shade trees abound in this part of Daventry.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 275
		y 30
		noun '/ceder'
		nsTop -1
		nsLeft 231
		nsBottom 61
		nsRight 319
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Beautiful shade trees abound in this part of Daventry.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 294
		y 85
		noun '/ceder'
		nsTop 61
		nsLeft 274
		nsBottom 109
		nsRight 315
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Beautiful shade trees abound in this part of Daventry.}
	)
)

(instance bush of NewFeature
	(properties
		x 18
		y 171
		noun '/bush'
		nsTop 154
		nsBottom 189
		nsRight 36
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is typical of the large, leafy vegetation found all throughout Daventry.}
	)
)

(instance bush2 of NewFeature
	(properties
		x 50
		y 179
		noun '/bush'
		nsTop 169
		nsLeft 35
		nsBottom 189
		nsRight 65
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is typical of the large, leafy vegetation found all throughout Daventry.}
	)
)

(instance wellBush of NewFeature
	(properties
		x 141
		y 133
		noun '/bush'
		nsTop 121
		nsLeft 128
		nsBottom 146
		nsRight 155
		description {bush on well}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {Several unremarkable plants have rooted themselves at the base of the old stone well.}
	)
)
