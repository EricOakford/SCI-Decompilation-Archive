;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1111)
(include game.sh)
(use Main)
(use String)
(use WalkTalk)
(use File)

(public
	proc1111_0 0
	proc1111_1 1
	proc1111_2 2
	proc1111_3 3
	proc1111_4 4
	proc1111_5 5
	proc1111_6 6
	proc1111_7 7
	proc1111_8 8
	proc1111_9 9
	proc1111_10 10
	proc1111_11 11
	proc1111_12 12
	proc1111_13 13
	proc1111_14 14
	proc1111_15 15
	proc1111_16 16
	proc1111_17 17
	proc1111_18 18
	proc1111_19 19
	proc1111_20 20
	proc1111_21 21
	proc1111_22 22
	proc1111_23 23
	proc1111_24 24
	proc1111_25 25
)

(local
	local0 =  -1
	[local1 17]
)
(procedure (proc1111_0 &tmp [temp0 8])
)

(procedure (proc1111_1 param1 param2 param3)
	(cond 
		((and (== argc 1) (not param1)) (global128 loop: 1 setLoop: 1 stop:))
		(
			(and
				(not (ResCheck 132 param1))
				(not (ResCheck 141 param1))
				(not (ResCheck 140 param1))
			)
		)
		((> argc 2)
			(global128
				number: param1
				loop: param2
				setLoop: param2
				play: 127 param3
			)
		)
		(else
			(global128
				number: param1
				loop: param2
				setLoop: param2
				play:
			)
		)
	)
)

(procedure (proc1111_2 param1)
	(if (ResCheck 141 param1) (Load RES_AUDIO param1))
	(if (ResCheck 140 param1) (Load RES_AUDIO36 param1))
	(if (ResCheck 132 param1) (Load RES_SOUND param1))
)

(procedure (proc1111_3)
	(switch (ego view?)
		(0 (ego heading: 90))
		(1 (ego heading: 270))
		(2 (ego heading: 180))
		(3 (ego heading: 360))
		(4 (ego heading: 135))
		(5 (ego heading: 225))
		(6 (ego heading: 45))
		(7 (ego heading: 315))
	)
)

(procedure (proc1111_4)
	(switch (ego view?)
		(0 (ego heading: 90))
		(1 (ego heading: 270))
		(2 (ego heading: 180))
		(3 (ego heading: 360))
		(4 (ego heading: 135))
		(5 (ego heading: 225))
		(6 (ego heading: 45))
		(7 (ego heading: 315))
	)
)

(procedure (proc1111_5)
	(switch (ego view?)
		(0 (ego heading: 90))
		(1 (ego heading: 270))
		(2 (ego heading: 180))
		(3 (ego heading: 360))
		(4 (ego heading: 135))
		(5 (ego heading: 225))
		(6 (ego heading: 45))
		(7 (ego heading: 315))
	)
)

(procedure (proc1111_6 param1 param2 &tmp temp0 temp1 temp2)
	(if gGRobot
		(if (and argc param1)
			(= temp1 param1)
		else
			(= temp1 0)
		)
		(if (> argc 1)
			(= temp2 param2)
			(WalkieTalkie killRobot: temp1 temp2)
		else
			(WalkieTalkie killRobot: temp1)
		)
		(= gGRobot 0)
	)
)

(procedure (proc1111_7 theGGRobot param2 param3 param4 param5 param6 param7 param8 &tmp temp0 temp1 temp2)
	(if gGRobot (proc1111_6))
	(= gGRobot theGGRobot)
	(if (> argc 1) (= temp1 param2) else (= temp1 0))
	(if (> argc 2) (= temp2 param3) else (= temp2 0))
	(WalkieTalkie
		doRobot:
			theGGRobot
			temp1
			temp2
			(if (> argc 3) param4 else 0)
			(if (> argc 7) param8 else 0)
			(if (> argc 4) param5 else 0)
			(if (> argc 5) param6 else -1)
			(if (> argc 6) param7 else 0)
	)
)

(procedure (proc1111_8 &tmp temp0 temp1)
	(proc1111_18 17)
	(= temp0 0)
	(while (< temp0 (cast size:))
		(= temp1 (cast at: temp0))
		(temp1 y: (- (temp1 y?) 1000))
		(if (temp1 isNotHidden:) (UpdateScreenItem temp1))
		(++ temp0)
	)
	(= temp0 0)
	(while (< temp0 (features size:))
		(= temp1 (features at: temp0))
		(temp1 y: (- (temp1 y?) 1000))
		(++ temp0)
	)
)

(procedure (proc1111_9 &tmp temp0 temp1)
	(= temp0 0)
	(while (< temp0 (cast size:))
		(= temp1 (cast at: temp0))
		(temp1 y: (+ (temp1 y?) 1000))
		(if (temp1 isNotHidden:)
			(temp1 doit:)
			(if (cast contains: temp1) (UpdateScreenItem temp1))
		)
		(++ temp0)
	)
	(= temp0 0)
	(while (< temp0 (features size:))
		(= temp1 (features at: temp0))
		(temp1 y: (+ (temp1 y?) 1000))
		(++ temp0)
	)
	(proc1111_19 17)
)

(procedure (proc1111_10)
	(switch (ego view?)
		(0 (ego heading: 90))
		(1 (ego heading: 270))
		(2 (ego heading: 180))
		(3 (ego heading: 360))
		(4 (ego heading: 135))
		(5 (ego heading: 225))
		(6 (ego heading: 45))
		(7 (ego heading: 315))
	)
)

(procedure (proc1111_11 &tmp temp0 temp1)
	(= temp0 1)
	(while (<= temp0 15)
		(if [local1 temp0]
			(while ([local1 temp0] size:)
				(DeleteLine
					(= temp1 ([local1 temp0] at: 0))
					(cast plane?)
				)
				([local1 temp0] delete: temp1)
			)
			(= [local1 temp0] 0)
		)
		(++ temp0)
	)
	(UpdatePlane (cast plane?))
	(FrameOut)
	(= local0 -1)
)

(procedure (proc1111_12)
	(switch (ego view?)
		(0 (ego heading: 90))
		(1 (ego heading: 270))
		(2 (ego heading: 180))
		(3 (ego heading: 360))
		(4 (ego heading: 135))
		(5 (ego heading: 225))
		(6 (ego heading: 45))
		(7 (ego heading: 315))
	)
)

(procedure (proc1111_13)
	(switch (ego view?)
		(0 (ego heading: 90))
		(1 (ego heading: 270))
		(2 (ego heading: 180))
		(3 (ego heading: 360))
		(4 (ego heading: 135))
		(5 (ego heading: 225))
		(6 (ego heading: 45))
		(7 (ego heading: 315))
	)
)

(procedure (proc1111_14 &tmp [temp0 7])
)

(procedure (proc1111_15)
	(if (not (prefFile scratch?)) (prefFile init:))
	(if (prefFile open: 2)
		(if (proc1111_20 19)
			(prefFile writeWord: 1)
		else
			(prefFile writeWord: 0)
		)
		(if (proc1111_20 20)
			(prefFile writeWord: 1)
		else
			(prefFile writeWord: 0)
		)
		(prefFile writeWord: gPrefFileReadWord)
		(prefFile writeWord: gPrefFileReadWord_2)
		(prefFile writeWord: gPrefFileReadWord_3)
		(prefFile close:)
	)
)

(procedure (proc1111_16)
	(if (not (prefFile scratch?)) (prefFile init:))
	(if (prefFile open: 1)
		(if (prefFile readWord:)
			(proc1111_18 19)
		else
			(proc1111_19 19)
		)
		(if (prefFile readWord:)
			(proc1111_18 20)
		else
			(proc1111_19 20)
		)
		(= gPrefFileReadWord (prefFile readWord:))
		(DoAudio 8 (= gPrefFileReadWord_2 (prefFile readWord:)))
		(DoSound SndMasterVol
			(= gPrefFileReadWord_3 (prefFile readWord:))
		)
		(prefFile close:)
	)
)

(procedure (proc1111_17 param1 param2 param3)
	(PlayVMD 0 param1 0)
	(PlayVMD 23 0 161)
	(PlayVMD 21 32 60 32 60)
	(if (> argc 1)
		(PlayVMD 1 32 60 param2)
	else
		(PlayVMD 1 32 60)
	)
	(PlayVMD 14 7)
	(if (and (== argc 3) param3) (curRoom drawPic: param3))
	(PlayVMD 6)
)

(procedure (proc1111_18 param1 &tmp temp0)
	(= temp0 (proc1111_20 param1 1))
	(= [global200 (/ param1 16)]
		(|
			[global200 (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
	temp0
)

(procedure (proc1111_19 param1 &tmp temp0)
	(= temp0 (proc1111_20 param1))
	(= [global200 (/ param1 16)]
		(&
			[global200 (/ param1 16)]
			(~ (>> $8000 (mod param1 16)))
		)
	)
	temp0
)

(procedure (proc1111_20 param1)
	(return
		(&
			[global200 (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
)

(procedure (proc1111_21 param1 param2 param3)
	(param1 at: param2 param3)
	(param1 at: (+ param2 1) (>> param3 $0008))
)

(procedure (proc1111_22 param1 param2)
	(return
		(+
			(& (param1 at: param2) $00ff)
			(<< (param1 at: (+ param2 1)) $0008)
		)
	)
)

(procedure (proc1111_23 &tmp autoRobotX autoRobotY temp2 autoRobotRobot)
	(return
		(if autoRobot
			(= temp2 (Robot 11))
			(= autoRobotRobot (autoRobot robot?))
			(= autoRobotX (autoRobot x?))
			(= autoRobotY (autoRobot y?))
			(proc1111_6)
			(WalkieTalkie
				showFrame: autoRobotRobot (+ temp2 1) autoRobotX autoRobotY
			)
			(return temp2)
		else
			0
		)
	)
)

(procedure (proc1111_24 param1)
	(return
		(^
			[global200 (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
)

(procedure (proc1111_25 param1)
	(= [global200 (/ param1 16)]
		(^
			[global200 (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
)

(instance prefFile of File
	(properties)
	
	(method (init)
		(= scratch (String with: curSaveDir))
		(scratch cat: {PREF.DAT})
		(= name (scratch data?))
		(scratch data: 0 dispose:)
	)
)
