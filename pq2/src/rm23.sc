;;; Sierra Script 1.0 - (do not remove this comment)
(script# 23)
(include system.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm23 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	;local6 unused
	local7
	nearJailer
	local9
	local10
	local11
	local12
	local13
	local14
	whichFile
	local16
	local17
)
(procedure (LocPrint)
	(Print &rest #at -1 125)
)

(procedure (DisplayFile)
	(= local9 1)
	(curRoom drawPic: 90 WIPERIGHT)
	(switch whichFile
		(0
			(mugShot1 posn: 52 62)
			(paperClip posn: 31 28)
			(if (== ((inventory at: iNewMugShot) owner?) 23)
				(removeableMugShot posn: 57 66)
			)
			(Animate (cast elements?) 0)
			(Display 23 0
				p_at 120 15
				p_width 200
				p_font 0
			)
			(Display 23 1
				p_at 20 72
				p_width 300
			)
		)
		(1
			(mugShot2 posn: 69 62)
			(Animate (cast elements?) 0)
			(Display 23 2
				p_at 120 15
				p_width 200
				p_font 0
			)
			(Display 23 3
				p_at 20 65
				p_width 300
			)
		)
	)
	(= local10 0)
	(User canInput: 1)
)

(procedure (NextPage)
	(= local9 0)
	(= local10 1)
	(curRoom drawPic: 90 WIPELEFT)
	(switch whichFile
		(0
			(mugShot1 posn: 0 0)
			(paperClip posn: 0 0)
			(if (== ((inventory at: 12) owner?) 23)
				(removeableMugShot posn: 0 0)
			)
			(Animate (cast elements?) 0)
			(Display 23 4 p_at 20 7 p_width 300 117)
		)
		(1
			(mugShot2 posn: 0 0)
			(Animate (cast elements?) 0)
			(Display 23 5 p_at 20 10 p_width 300 117)
		)
	)
)

(instance mugShot1 of View
	(properties)
)

(instance mugShot2 of View
	(properties)
)

(instance removeableMugShot of View
	(properties)
)

(instance paperClip of View
	(properties)
)

(instance jailer of Actor
	(properties)
)

(instance witness of Actor
	(properties)
)

(instance witnessTalking of Sound
	(properties
		number 12
	)
)

(instance rm23 of Room
	(properties
		picture 23
		style HWIPE
	)
	
	(method (init)
		(super init:)
		(if (>= gamePhase 2) (= local0 1))
		(User canInput: 1 canControl: 1)
		(Load VIEW 1)
		(Load VIEW 87)
		(Load VIEW 50)
		(Load VIEW 204)
		(= nearJailer 1)
		(= gunFireState 3)
		(mugShot1
			view: 204
			loop: 2
			cel: 2
			posn: 0 0
			init:
		)
		(removeableMugShot
			view: 204
			loop: 2
			cel: 2
			posn: 0 0
			setPri: 12
			init:
		)
		(mugShot2
			view: 204
			loop: 2
			cel: 1
			posn: 0 0
			init:
		)
		(paperClip
			view: 204
			loop: 2
			cel: 3
			posn: 0 0
			setPri: 14
			init:
		)
		(witness
			view: 87
			illegalBits: 0
			posn: 69 112
			loop: 0
			cel: 0
			setPri: 6
			setCycle: Walk
			init:
			stopUpd:
			setScript: witnessScript
		)
		(jailer
			view: 50
			posn: 275 129
			loop: 1
			cel: 0
			setPri: 6
			illegalBits: 0
			init:
			setScript: jailerScript
		)
		(self setLocales: regFieldKit)
		(self setScript: rm23Script)
	)
	
	(method (dispose)
		(rm23Script dispose:)
		(jailerScript dispose:)
		(witnessScript dispose:)
		(folderScript dispose:)
		(super dispose:)
	)
)

(instance rm23Script of Script
	(properties)
	
	(method (doit)
		(cond 
			((> (ego y?) 160)
				(if (== ((inventory at: 10) owner?) 23)
					(ego get: iFieldKit)
					(LocPrint 23 6)
				)
				(if (== ((inventory at: iHandGun) owner?) 23)
					(ego get: iHandGun)
					(if (> dollars 5)
						(= dollars (- dollars 5))
						(LocPrint 23 7)
					else
						(LocPrint 23 8)
						(LocPrint 23 9)
						(LocPrint 23 10)
					)
				)
				(if (== gamePhase 1)
					(= captainWarningTimer 0)
					(= global159 0)
					(= gamePhase 2)
				)
				(curRoom newRoom: 22)
			)
			(
				(and
					(< (ego x?) 200)
					local1
					(not local2)
				)
				(witnessScript changeState: 1)
			)
			(
				(and
					(> (ego x?) 210)
					local4
				)
				(witnessScript changeState: 6)
			)
			(
				(and
					nearJailer
					(< (ego x?) 210)
				)
				(= nearJailer 0)
				(jailerScript changeState: 4)
			)
			(
				(and
					(> (ego x?) 210)
					local7
					(not nearJailer)
				)
				(= nearJailer 1)
				(jailerScript changeState: 6)
			)
		)
		(= local5
			(if (ego inRect: 122 121 163 136)
				(!= (ego loop?) 2)
			else
				0
			)
		)
		(cond 
			((> local16 1)
				(-- local16)
			)
			((== local16 1)
				(= local16 0)
				(jailerScript cue:)
			)
			((> local17 0)
				(-- local17)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local11
					(ego
						view: 1
						setStep: 3 2
						setLoop: -1
						posn: 239 130
						init:
					)
					(jailerScript changeState: 10)
				else
					(ego
						view: 1
						setStep: 3 2
						setLoop: -1
						posn: 239 150
						init:
						setMotion: MoveTo 239 130
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					(local12
						(cond 
							((Said 'get/shot,mugshot,painting')
								(if (== whichFile 0)
									(if (== ((inventory at: iNewMugShot) owner?) 23)
										(removeableMugShot dispose:)
										(ego get: iNewMugShot)
										(SolvePuzzle 2)
									else
										(LocPrint 23 11)
									)
								else
									(LocPrint 23 12)
								)
							)
							((Said '[look,see,read,turn,go]/end,next,2,(page[<next,second])')
								(if local9 (NextPage) else (LocPrint 23 13))
							)
							((Said 'look/shot,mugshot,painting') (if local9 (LocPrint 23 14) else (DisplayFile)))
							((Said '[look,see,read,turn,go]/back,preceding,1,(page<(preceding,first))')
								(if local10
									(DisplayFile)
								else
									(LocPrint 23 15)
								)
							)
							(
								(or
									(Said '[turn,go,look]/(page<next,third),(3<page)')
									(Said 'read,look,see,turn/(page[<third]),(3<page)')
								)
								(Print 23 16 #at -1 150)
							)
							(
								(or
									(Said 'exit,close,replace[/file,file]')
									(Said 'deposit,gave[<back]/file,file')
								)
								(if (== whichFile 0)
									(mugShot1 posn: 0 0)
									(paperClip posn: 0 0)
									(if (not (ego has: 12)) (removeableMugShot posn: 0 0))
								else
									(mugShot2 posn: 0 0)
								)
								(folderScript changeState: 2)
							)
						)
					)
					((Said 'look>')
						(cond 
							((Said '[<around,at][/chamber,jail]') (LocPrint 23 17))
							((Said '[<down,at][/floor]') (LocPrint 23 18))
							((Said '[<up,at][/ceiling,roof]') (LocPrint 23 19))
							((Said '/jailer,(cop[<correctional])')
								(cond 
									((not nearJailer) (LocPrint 23 20))
									((== gamePhase 0) (LocPrint 23 21))
									(else (LocPrint 23 22))
								)
							)
							((Said '/wall') (LocPrint 23 23))
							((Said '/file')
								(if nearJailer
									(if (>= gamePhase 1)
										(LocPrint 23 24)
									else
										(LocPrint 23 25)
									)
								else
									(LocPrint 23 26)
								)
							)
							((Said '/dude')
								(cond 
									(nearJailer
										(if (>= gamePhase 1)
											(LocPrint 23 22)
										else
											(LocPrint 23 21)
										)
									)
									(local2
										(if local5
											(LocPrint 23 27)
										else
											(LocPrint 23 28)
										)
									)
									(else (LocPrint 23 20))
								)
							)
							((Said '/convict,witness,saxton')
								(cond 
									(
										(and
											(>= gamePhase 1)
											nearJailer
											(not local3)
											(not local4)
										)
										(= local1 1)
										(if local0
											(LocPrint 23 29)
										else
											(= local0 1)
											(LocPrint 23 30)
											(LocPrint 23 31)
											(SolvePuzzle 1 fAskedToSeeWitness)
										)
									)
									(local2
										(cond 
											(nearJailer
												(LocPrint 23 32)
											)
											(local5
												(LocPrint 23 27)
											)
											(else
												(LocPrint 23 28)
											)
										)
									)
									(else
										(LocPrint 23 20)
									)
								)
							)
						)
					)
					((Said 'chat/angel<death')
						(if nearJailer
							(Print 23 33)
						else
							(Print 23 34)
						)
					)
					((Said 'chat/broad')
						(LocPrint 23 35)
					)
					(
						(or
							(Said 'see,display/witness,saxton')
							(Said 'interrogate,ask/jailer,cop,dude/witness,saxton')
						)
						(cond 
							((>= gamePhase 1)
								(cond 
									(
										(and
											nearJailer
											(not local2)
											(not local4)
										)
										(= local1 1)
										(if local0
											(LocPrint 23 29)
										else
											(= local0 1)
											(LocPrint 23 30)
											(LocPrint 23 31)
											(SolvePuzzle 1 fAskedToSeeWitness)
										)
									)
									(local2
										(cond 
											(nearJailer
												(LocPrint 23 36)
											)
											(local5
												(LocPrint 23 37)
											)
											(else
												(LocPrint 23 28)
											)
										)
									)
									(else
										(LocPrint 23 20)
									)
								)
							)
							(nearJailer
								(LocPrint 23 25)
							)
							(else
								(LocPrint 23 26)
							)
						)
					)
					((Said 'interrogate,chat/jailer,cop')
						(if nearJailer
							(LocPrint 23 25)
						else
							(LocPrint 23 26)
						)
					)
					(
						(or
							(Said 'call,ask,chat,interrogate/witness,saxton')
							(Said 'have<do/witness')
							(Said 'ask,chat,describe/kidnapping,clue,escape,incident')
							(Said 'interrogate,ask,chat/dude,jailer,cop/clue,kidnapping,escape,incident')
						)
						(cond 
							(
								(and
									nearJailer
									(>= gamePhase 1)
									(not local3)
									(not local4)
								)
								(= local1 1)
								(if local0
									(LocPrint 23 29)
								else
									(= local0 1)
									(LocPrint 23 30)
									(LocPrint 23 31)
									(SolvePuzzle 1 fAskedToSeeWitness)
								)
							)
							((>= gamePhase 1)
								(cond 
									(local3
										(if local5
											(if (Btst fInterrogatedWitness)
												(LocPrint 23 38)
												(= local4 1)
												(= local3 0)
											else
												(SolvePuzzle 2 fInterrogatedWitness)
												(witnessTalking play:)
												(LocPrint 23 39)
												(Print 23 40 #at -1 125)
												(Print 23 41 #at -1 125)
												(= local4 1)
												(= local3 0)
											)
										else
											(LocPrint 23 28)
										)
									)
									((> (ego x?) 210)
										(LocPrint 23 42)
									)
									(local4
										(LocPrint 23 43)
									)
									(else
										(LocPrint 23 26)
									)
								)
							)
							(else
								(LocPrint 23 44)
							)
						)
					)
					((Said 'affirmative')
						(if (and
								local2
								(not nearJailer)
							)
							(LocPrint 23 45)
						else
							(LocPrint 23 46)
						)
					)
					((Said 'no')
						(if
							(and
								local2
								(not nearJailer)
							)
							(LocPrint 23 47)
						else
							(LocPrint 23 48)
						)
					)
					((Said 'open/door')
						(LocPrint 23 49)
					)
					(
						(or
							(Said 'chat/bains')
							(Said 'chat,ask[/dude,jailer,cop,witness,convict,saxton]/bains')
						)
						(if (< gamePhase 1)
							(LocPrint 23 50)
							(Print 23 51 #at -1 125)
						else
							(LocPrint 23 52)
						)
					)
					(
						(or
							(Said '[gave,get]/description[<auto]')
							(Said 'gave,get/description[<auto]/i')
							(Said 'describe,ask,chat[<about]/auto')
							(Said 'ask/jailer,dude,cop/auto')
						)
						(cond 
							(nearJailer
								(if (>= gamePhase 1)
									(SolvePuzzle 1 fGetJailerCarDescription)
									(LocPrint 23 53)
								else
									(LocPrint 23 25)
								)
							)
							(local5
								(if local3
									(LocPrint 23 54)
								else
									(LocPrint 23 26)
								)
							)
							(else
								(LocPrint 23 28)
							)
						)
					)
					((Said 'chat/jailer,cop[<correctional]')
						(if nearJailer
							(LocPrint 23 25)
						else
							(LocPrint 23 26)
						)
					)
					((Said 'open,unlock/cell,cage')
						(cond 
							(nearJailer
								(LocPrint 23 55)
							)
							(local3 (LocPrint 23 56))
							(else (LocPrint 23 26))
						)
					)
					((Said 'interrogate,chat,see/convict')
						(cond 
							(nearJailer
								(if
									(and
										(not local1)
										(not local3)
										(not local4)
									)
									(LocPrint 23 57)
									(= local17 50)
								else
									(LocPrint 23 20)
								)
							)
							(local2
								(if local5
									(if local4
										(LocPrint 23 43)
									else
										(LocPrint 23 58)
									)
								else
									(LocPrint 23 28)
								)
							)
							(else (LocPrint 23 59))
						)
					)
					(
						(or
							(Said 'witness,see,chat,ask/incident,kidnapping,escape')
							(Said 'tell,chat,interrogate,ask/i,saxton,jailer,convict,witness,dude/incident,kidnapping,escape')
							(Said 'witness,see<did/kidnapping,escape,incident')
							(Said '(see<did)<what')
							(Said '(see<did)/anything')
						)
						(if (>= gamePhase 1)
							(cond 
								(local5
									(cond 
										(local3
											(if (Btst fInterrogatedWitness)
												(LocPrint 23 38)
												(= local4 1)
												(= local3 0)
											else
												(SolvePuzzle 2 fInterrogatedWitness)
												(witnessTalking play: loop: 2)
												(LocPrint 23 39)
												(Print 23 40 #at -1 125)
												(Print 23 41 #at -1 125)
												(= local4 1)
												(= local3 0)
											)
										)
										(nearJailer
											(LocPrint 23 42)
										)
										(local4
											(LocPrint 23 43)
										)
										(else
											(LocPrint 23 26)
										)
									)
								)
								(nearJailer (LocPrint 23 42))
								(else (LocPrint 23 28))
							)
						else
							(LocPrint 23 58)
						)
					)
					(
						(or
							(Said
								'chat,ask/jailer,cop,witness,saxton,convict,dude/knife'
							)
							(Said 'chat,ask/knife')
							(Said '(see[<did])/knife')
						)
						(cond 
							(nearJailer
								(if (>= gamePhase 1)
									(LocPrint 23 60)
								else
									(LocPrint 23 61)
								)
							)
							(local2
								(if local5
									(LocPrint 23 62)
								else
									(LocPrint 23 28)
								)
							)
							(else (LocPrint 23 26))
						)
					)
					(
						(or
							(Said '[interrogate,look,see,get,ask]/bains')
							(Said '[look,see,get,ask]/file<bains')
							(Said 'gave,display,get/[i,jailer,cop]/file<bains')
							(Said 'ask[/dude,jailer,cop]/file<bains')
						)
						(cond 
							(nearJailer
								(cond 
									((> local17 0)
										(if (>= gamePhase 1)
											(LocPrint 23 63)
										else
											(LocPrint 23 50)
											(Print 23 51 #at -1 125)
										)
									)
									((>= gamePhase 1)
										(if
										(or (== (mod (++ local13) 2) 1) (< local13 2))
											(if (> local13 1)
												(LocPrint 23 64)
											else
												(LocPrint 23 65)
											)
											(= whichFile 0)
											(jailerScript changeState: 7)
										else
											(LocPrint 23 66)
										)
									)
									((< gamePhase 1) (LocPrint 23 50) (Print 23 51 #at -1 125))
								)
							)
							(local2
								(if local5
									(LocPrint 23 67)
								else
									(LocPrint 23 28)
								)
							)
							(else (LocPrint 23 26))
						)
					)
					(
						(or
							(Said '[look,see,get,ask]/luis')
							(Said '[look,see,get,ask]/file<luis')
							(Said 'gave,display,get/[i,jailer,cop]/file<luis')
							(Said 'ask[/dude,jailer,cop]/file<luis')
							(Said 'ask/jailer/!*')
						)
						(cond 
							(nearJailer
								(cond 
									((> local17 0) (LocPrint 23 68))
									((== gamePhase 0) (LocPrint 23 69))
									((>= gamePhase 1)
										(if
										(or (== (mod (++ local14) 2) 1) (< local14 2))
											(if (> local14 1)
												(LocPrint 23 64)
											else
												(LocPrint 23 70)
											)
											(= whichFile 1)
											(SolvePuzzle 2 117)
											(jailerScript changeState: 7)
										else
											(LocPrint 23 66)
										)
									)
								)
							)
							(
								(or
									local3
									local4
								)
								(if local5
									(LocPrint 23 71)
								else
									(LocPrint 23 28)
								)
							)
							(else
								(LocPrint 23 26)
							)
						)
					)
					((Said 'display,flash/badge')
						(cond 
							(nearJailer
								(LocPrint 23 72)
							)
							(local3
								(if local5
									(LocPrint 23 72)
								else
									(LocPrint 23 28)
								)
							)
							(else
								(LocPrint 23 73)
							)
						)
					)
					(
						(or
							(Said 'see,look,display,get,ask/file')
							(Said 'have<do/file')
						)
						(if nearJailer
							(if (>= gamePhase 1)
								(LocPrint 23 24)
							else
								(LocPrint 23 25)
							)
						else
							(LocPrint 23 26)
						)
					)
					((Said 'interrogate,chat/dude')
						(cond 
							(nearJailer
								(LocPrint 23 25)
							)
							(
								(and
									local2
									(or local3 local4)
								)
								(if local5
									(LocPrint 23 58)
								else
									(LocPrint 23 28)
								)
							)
							(else (LocPrint 23 26))
						)
					)
					((Said '[see,chat]/*')
						(if (> local17 0)
							(LocPrint 23 74)
						else
							(event claimed: 0)
						)
					)
					(
						(or
							(Said 'get[<back]/9mm[/jailer,cop]')
							(Said 'gave[<back]/9mm')
							(Said 'gave[/i]/9mm')
							(Said 'replace/9mm')
							(Said 'ask[/dude,jailer,cop]/9mm')
							(Said 'ask/9mm')
						)
						(if nearJailer
							(if (== ((inventory at: iHandGun) owner?) 23)
								(jailerScript changeState: 13)
							else
								(LocPrint 23 75)
							)
						else
							(LocPrint 23 26)
						)
					)
					(
						(or
							(Said 'get[<back]/briefcase[/jailer,cop]')
							(Said 'gave[<back]/briefcase')
							(Said 'gave[/i]/briefcase')
							(Said 'replace/briefcase')
							(Said 'ask[/dude,jailer,cop]/briefcase')
							(Said 'ask/briefcase')
						)
						(if nearJailer
							(if (== ((inventory at: iFieldKit) owner?) 23)
								(LocPrint 23 76)
								(ego get: iFieldKit)
								(jailerScript changeState: 12)
							else
								(LocPrint 23 77)
							)
						)
					)
					(
						(or
							(Said 'close,replace/file,file')
							(Said 'gave[<back]/file,file')
						)
						(LocPrint 23 78)
					)
				)
			)
		)
	)
)

(instance folderScript of Script
	(properties)
	
	(method (init)
		(User canInput: 1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(jailer hide:)
				(ego hide:)
				(witness hide:)
				(self cue:)
			)
			(1
				(= local9 1)
				(= local10 0)
				(DisplayFile)
			)
			(2
				(jailer show:)
				(ego show:)
				(witness show:)
				(HandsOn)
				(= local11 1)
				(= local12 0)
				(User canControl: 1)
				(curRoom drawPic: 23 8)
				(SetMenu 513 112 1) ;saveI smMENU_ENABLE ENABLED
				(rm23Script changeState: 0)
			)
		)
	)
)

(instance witnessScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (witness setLoop: 0))
			(1
				(= local1 0)
				(= local2 1)
				(witness
					cycleSpeed: 1
					setMotion: MoveTo 153 112 self
					startUpd:
				)
			)
			(2
				(witness setLoop: 2 setMotion: MoveTo 153 114 self)
			)
			(3
				(witness
					setPri: 7
					setLoop: 1
					setMotion: MoveTo 146 114 self
				)
			)
			(4
				(witness setLoop: 2 setMotion: MoveTo 142 116 self)
			)
			(5
				(witness stopUpd:)
				(= local3 1)
				(if (Btst fInterrogatedWitness)
					(LocPrint 23 79 83)
				else
					(LocPrint 23 80 83)
				)
			)
			(6
				(= local4 0)
				(witness
					startUpd:
					setLoop: 3
					setMotion: MoveTo 146 114 self
				)
			)
			(7
				(witness
					setLoop: 0
					setMotion: MoveTo 153 114 self
				)
			)
			(8
				(witness
					setLoop: 3
					setMotion: MoveTo 153 112 self
				)
			)
			(9
				(witness
					setPri: 6
					setLoop: 1
					setMotion: MoveTo 69 112 self
				)
			)
			(10
				(= local2 0)
				(witness
					setLoop: 0
					setMotion: 0
					stopUpd:
				)
			)
		)
	)
)

(instance jailerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(jailer setMotion: MoveTo 248 125 self startUpd:)
				(User canInput: 0)
			)
			(1
				(if (== gamePhase 1)
					(Print 23 81 #at -1 120 #draw)
					(Print 23 82 #at -1 120 #draw)
					(Print 23 83 #at -1 120 #draw)
					(= local7 1)
					(= isOnDuty 0)
					(if (ego has: iHandGun)
						(LocPrint 23 84 83)
					)
					(self cue:)
				else
					(self cue:)
				)
			)
			(2
				(User canInput: 1)
				(if (ego has: iHandGun)
					(LocPrint 23 85 83)
					(LocPrint 23 86 83)
					(LocPrint 23 87 83)
					(LocPrint 23 88 83)
					(ego put: iHandGun 23)
					(self cue:)
				else
					(self cue:)
				)
			)
			(3
				(if (ego has: iFieldKit)
					(Print 23 89 #at -1 120)
					(LocPrint 23 90)
					(ego put: iFieldKit 23)
				)
				(if (== ((inventory at: iHandGun) owner?) 23)
					(LocPrint 23 91)
				)
				(cond 
					((>= gamePhase 2)
						(Print 23 92 #at -1 125 #draw)
						(= local7 1)
					)
					((< gamePhase 1)
						(LocPrint 23 93 83)
						(= local7 1)
					)
				)
			)
			(4
				(jailer setMotion: MoveTo 275 129 self)
			)
			(5
				(jailer stopUpd:)
			)
			(6
				(jailer
					setMotion: MoveTo 248 125
					startUpd:
				)
			)
			(7
				(HandsOff)
				(jailer
					setLoop: 0
					setMotion: MoveTo 275 129
					startUpd:
				)
				(= local16 30)
			)
			(8
				(jailer
					setLoop: 1
					setMotion: MoveTo 248 125 self
				)
			)
			(9
				(LocPrint 23 94 83)
				(if (== whichFile 0)
					(LocPrint 23 95 83)
				else
					(LocPrint 23 96 83)
				)
				(= local12 1)
				(SetMenu 513 112 0) ;disable saving
				(folderScript changeState: 0)
			)
			(10
				(HandsOff)
				(LocPrint 23 97)
				(LocPrint 23 98)
				(jailer
					setLoop: 0
					setMotion: MoveTo 275 129
					startUpd:
				)
				(= local16 20)
			)
			(11
				(jailer
					setLoop: -1
					setMotion: MoveTo 248 125
				)
				(HandsOn)
			)
			(12
				(HandsOff)
				(if (== ((inventory at: 0) owner?) 23)
					(self cue:)
				else
					(ego setMotion: MoveTo 228 127)
					(self changeState: 14)
				)
			)
			(13
				(HandsOff)
				(LocPrint 23 99)
				(LocPrint 23 100)
				(if (> dollars 5) (= dollars (- dollars 5)))
				(if (== ((inventory at: iFieldKit) owner?) 23)
					(LocPrint 23 76)
					(ego get: iFieldKit)
				)
				(ego
					get: 0
					setMotion: MoveTo 228 127 self
				)
			)
			(14
				(if (== gamePhase 1)
					(= gamePhase 2)
					(= captainWarningTimer 0)
				)
				(ego
					illegalBits: 0
					setMotion: MoveTo 239 160 self
				)
			)
			(15
				(HandsOn)
				(ego illegalBits: cWHITE) ;-32768)
				(curRoom newRoom: 22)
			)
		)
	)
)
