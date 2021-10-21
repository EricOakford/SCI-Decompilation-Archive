;;; Sierra Script 1.0 - (do not remove this comment)
(script# 23)
(include sci.sh)
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
	local6
	local7
	local8
	local9
	local10
	local11
	local12
	local13
	local14
	local15
	local16
	local17
)
(procedure (localproc_000c)
	(Print &rest #at -1 125)
)

(procedure (localproc_03d8)
	(= local9 1)
	(curRoom drawPic: 90 3)
	(switch local15
		(0
			(mugShot1 posn: 52 62)
			(paperClip posn: 31 28)
			(if (== ((inventory at: 12) owner?) 23)
				(removeableMugShot posn: 57 66)
			)
			(Animate (cast elements?) 0)
			(Display 23 0 dsCOORD 120 15 dsWIDTH 200 dsFONT 0)
			(Display 23 1 dsCOORD 20 72 dsWIDTH 300)
		)
		(1
			(mugShot2 posn: 69 62)
			(Animate (cast elements?) 0)
			(Display 23 2 dsCOORD 120 15 dsWIDTH 200 dsFONT 0)
			(Display 23 3 dsCOORD 20 65 dsWIDTH 300)
		)
	)
	(= local10 0)
	(User canInput: 1)
)

(procedure (localproc_04c1)
	(= local9 0)
	(= local10 1)
	(curRoom drawPic: 90 2)
	(switch local15
		(0
			(mugShot1 posn: 0 0)
			(paperClip posn: 0 0)
			(if (== ((inventory at: 12) owner?) 23)
				(removeableMugShot posn: 0 0)
			)
			(Animate (cast elements?) 0)
			(Display 23 4 dsCOORD 20 7 dsWIDTH 300 117)
		)
		(1
			(mugShot2 posn: 0 0)
			(Animate (cast elements?) 0)
			(Display 23 5 dsCOORD 20 10 dsWIDTH 300 117)
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

(instance jailer of Act
	(properties)
)

(instance witness of Act
	(properties)
)

(instance witnessTalking of Sound
	(properties
		number 12
	)
)

(instance rm23 of Rm
	(properties
		picture 23
		style $0000
	)
	
	(method (init)
		(super init:)
		(if (>= gamePhase 2) (= local0 1))
		(User canInput: 1 canControl: 1)
		(Load rsVIEW 1)
		(Load rsVIEW 87)
		(Load rsVIEW 50)
		(Load rsVIEW 204)
		(= local8 1)
		(= gunFireState 3)
		(mugShot1 view: 204 loop: 2 cel: 2 posn: 0 0 init:)
		(removeableMugShot
			view: 204
			loop: 2
			cel: 2
			posn: 0 0
			setPri: 12
			init:
		)
		(mugShot2 view: 204 loop: 2 cel: 1 posn: 0 0 init:)
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
		(self setLocales: 153)
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
					(ego get: 10)
					(localproc_000c 23 6)
				)
				(if (== ((inventory at: 0) owner?) 23)
					(ego get: 0)
					(if (> dollars 5)
						(= dollars (- dollars 5))
						(localproc_000c 23 7)
					else
						(localproc_000c 23 8)
						(localproc_000c 23 9)
						(localproc_000c 23 10)
					)
				)
				(if (== gamePhase 1)
					(= captainWarningTimer 0)
					(= global159 0)
					(= gamePhase 2)
				)
				(curRoom newRoom: 22)
			)
			((and (< (ego x?) 200) local1 (not local2)) (witnessScript changeState: 1))
			((and (> (ego x?) 210) local4) (witnessScript changeState: 6))
			((and local8 (< (ego x?) 210)) (= local8 0) (jailerScript changeState: 4))
			((and (> (ego x?) 210) local7 (not local8)) (= local8 1) (jailerScript changeState: 6))
		)
		(= local5
			(if (ego inRect: 122 121 163 136)
				(!= (ego loop?) 2)
			else
				0
			)
		)
		(cond 
			((> local16 1) (-- local16))
			((== local16 1) (= local16 0) (jailerScript cue:))
			((> local17 0) (-- local17))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local11
					(ego view: 1 setStep: 3 2 setLoop: -1 posn: 239 130 init:)
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
			(evSAID
				(cond 
					(local12
						(cond 
							((Said 'get/shot,mugshot,painting')
								(if (== local15 0)
									(if (== ((inventory at: 12) owner?) 23)
										(removeableMugShot dispose:)
										(ego get: 12)
										(SolvePuzzle 2)
									else
										(localproc_000c 23 11)
									)
								else
									(localproc_000c 23 12)
								)
							)
							(
								(Said
									'[look,see,read,turn,go]/end,next,2,(page[<next,second])'
								)
								(if local9 (localproc_04c1) else (localproc_000c 23 13))
							)
							((Said 'look/shot,mugshot,painting') (if local9 (localproc_000c 23 14) else (localproc_03d8)))
							(
								(Said
									'[look,see,read,turn,go]/back,preceding,1,(page<(preceding,first))'
								)
								(if local10
									(localproc_03d8)
								else
									(localproc_000c 23 15)
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
								(if (== local15 0)
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
							((Said '[<around,at][/chamber,jail]') (localproc_000c 23 17))
							((Said '[<down,at][/floor]') (localproc_000c 23 18))
							((Said '[<up,at][/ceiling,roof]') (localproc_000c 23 19))
							((Said '/jailer,(cop[<correctional])')
								(cond 
									((not local8) (localproc_000c 23 20))
									((== gamePhase 0) (localproc_000c 23 21))
									(else (localproc_000c 23 22))
								)
							)
							((Said '/wall') (localproc_000c 23 23))
							((Said '/file')
								(if local8
									(if (>= gamePhase 1)
										(localproc_000c 23 24)
									else
										(localproc_000c 23 25)
									)
								else
									(localproc_000c 23 26)
								)
							)
							((Said '/dude')
								(cond 
									(local8
										(if (>= gamePhase 1)
											(localproc_000c 23 22)
										else
											(localproc_000c 23 21)
										)
									)
									(local2
										(if local5
											(localproc_000c 23 27)
										else
											(localproc_000c 23 28)
										)
									)
									(else (localproc_000c 23 20))
								)
							)
							((Said '/convict,witness,saxton')
								(cond 
									(
									(and (>= gamePhase 1) local8 (not local3) (not local4))
										(= local1 1)
										(if local0
											(localproc_000c 23 29)
										else
											(= local0 1)
											(localproc_000c 23 30)
											(localproc_000c 23 31)
											(SolvePuzzle 1 69)
										)
									)
									(local2
										(cond 
											(local8 (localproc_000c 23 32))
											(local5 (localproc_000c 23 27))
											(else (localproc_000c 23 28))
										)
									)
									(else (localproc_000c 23 20))
								)
							)
						)
					)
					((Said 'chat/angel<death') (if local8 (Print 23 33) else (Print 23 34)))
					((Said 'chat/broad') (localproc_000c 23 35))
					(
						(or
							(Said 'see,display/witness,saxton')
							(Said 'interrogate,ask/jailer,cop,dude/witness,saxton')
						)
						(cond 
							((>= gamePhase 1)
								(cond 
									((and local8 (not local2) (not local4))
										(= local1 1)
										(if local0
											(localproc_000c 23 29)
										else
											(= local0 1)
											(localproc_000c 23 30)
											(localproc_000c 23 31)
											(SolvePuzzle 1 69)
										)
									)
									(local2
										(cond 
											(local8 (localproc_000c 23 36))
											(local5 (localproc_000c 23 37))
											(else (localproc_000c 23 28))
										)
									)
									(else (localproc_000c 23 20))
								)
							)
							(local8 (localproc_000c 23 25))
							(else (localproc_000c 23 26))
						)
					)
					((Said 'interrogate,chat/jailer,cop')
						(if local8
							(localproc_000c 23 25)
						else
							(localproc_000c 23 26)
						)
					)
					(
						(or
							(Said 'call,ask,chat,interrogate/witness,saxton')
							(Said 'have<do/witness')
							(Said 'ask,chat,describe/kidnapping,clue,escape,incident')
							(Said
								'interrogate,ask,chat/dude,jailer,cop/clue,kidnapping,escape,incident'
							)
						)
						(cond 
							(
							(and local8 (>= gamePhase 1) (not local3) (not local4))
								(= local1 1)
								(if local0
									(localproc_000c 23 29)
								else
									(= local0 1)
									(localproc_000c 23 30)
									(localproc_000c 23 31)
									(SolvePuzzle 1 69)
								)
							)
							((>= gamePhase 1)
								(cond 
									(local3
										(if local5
											(if (Btst 70)
												(localproc_000c 23 38)
												(= local4 1)
												(= local3 0)
											else
												(SolvePuzzle 2 70)
												(witnessTalking play:)
												(localproc_000c 23 39)
												(Print 23 40 #at -1 125)
												(Print 23 41 #at -1 125)
												(= local4 1)
												(= local3 0)
											)
										else
											(localproc_000c 23 28)
										)
									)
									((> (ego x?) 210) (localproc_000c 23 42))
									(local4 (localproc_000c 23 43))
									(else (localproc_000c 23 26))
								)
							)
							(else (localproc_000c 23 44))
						)
					)
					((Said 'affirmative')
						(if (and local2 (not local8))
							(localproc_000c 23 45)
						else
							(localproc_000c 23 46)
						)
					)
					((Said 'n')
						(if (and local2 (not local8))
							(localproc_000c 23 47)
						else
							(localproc_000c 23 48)
						)
					)
					((Said 'open/door') (localproc_000c 23 49))
					(
						(or
							(Said 'chat/bains')
							(Said
								'chat,ask[/dude,jailer,cop,witness,convict,saxton]/bains'
							)
						)
						(if (< gamePhase 1)
							(localproc_000c 23 50)
							(Print 23 51 #at -1 125)
						else
							(localproc_000c 23 52)
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
							(local8
								(if (>= gamePhase 1)
									(SolvePuzzle 1 110)
									(localproc_000c 23 53)
								else
									(localproc_000c 23 25)
								)
							)
							(local5
								(if local3
									(localproc_000c 23 54)
								else
									(localproc_000c 23 26)
								)
							)
							(else (localproc_000c 23 28))
						)
					)
					((Said 'chat/jailer,cop[<correctional]')
						(if local8
							(localproc_000c 23 25)
						else
							(localproc_000c 23 26)
						)
					)
					((Said 'open,unlock/cell,cage')
						(cond 
							(local8 (localproc_000c 23 55))
							(local3 (localproc_000c 23 56))
							(else (localproc_000c 23 26))
						)
					)
					((Said 'interrogate,chat,see/convict')
						(cond 
							(local8
								(if (and (not local1) (not local3) (not local4))
									(localproc_000c 23 57)
									(= local17 50)
								else
									(localproc_000c 23 20)
								)
							)
							(local2
								(if local5
									(if local4
										(localproc_000c 23 43)
									else
										(localproc_000c 23 58)
									)
								else
									(localproc_000c 23 28)
								)
							)
							(else (localproc_000c 23 59))
						)
					)
					(
						(or
							(Said 'witness,see,chat,ask/incident,kidnapping,escape')
							(Said
								'tell,chat,interrogate,ask/i,saxton,jailer,convict,witness,dude/incident,kidnapping,escape'
							)
							(Said 'witness,see<did/kidnapping,escape,incident')
							(Said '(see<did)<what')
							(Said '(see<did)/anything')
						)
						(if (>= gamePhase 1)
							(cond 
								(local5
									(cond 
										(local3
											(if (Btst 70)
												(localproc_000c 23 38)
												(= local4 1)
												(= local3 0)
											else
												(SolvePuzzle 2 70)
												(witnessTalking play: loop: 2)
												(localproc_000c 23 39)
												(Print 23 40 #at -1 125)
												(Print 23 41 #at -1 125)
												(= local4 1)
												(= local3 0)
											)
										)
										(local8 (localproc_000c 23 42))
										(local4 (localproc_000c 23 43))
										(else (localproc_000c 23 26))
									)
								)
								(local8 (localproc_000c 23 42))
								(else (localproc_000c 23 28))
							)
						else
							(localproc_000c 23 58)
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
							(local8
								(if (>= gamePhase 1)
									(localproc_000c 23 60)
								else
									(localproc_000c 23 61)
								)
							)
							(local2
								(if local5
									(localproc_000c 23 62)
								else
									(localproc_000c 23 28)
								)
							)
							(else (localproc_000c 23 26))
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
							(local8
								(cond 
									((> local17 0)
										(if (>= gamePhase 1)
											(localproc_000c 23 63)
										else
											(localproc_000c 23 50)
											(Print 23 51 #at -1 125)
										)
									)
									((>= gamePhase 1)
										(if
										(or (== (mod (++ local13) 2) 1) (< local13 2))
											(if (> local13 1)
												(localproc_000c 23 64)
											else
												(localproc_000c 23 65)
											)
											(= local15 0)
											(jailerScript changeState: 7)
										else
											(localproc_000c 23 66)
										)
									)
									((< gamePhase 1) (localproc_000c 23 50) (Print 23 51 #at -1 125))
								)
							)
							(local2
								(if local5
									(localproc_000c 23 67)
								else
									(localproc_000c 23 28)
								)
							)
							(else (localproc_000c 23 26))
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
							(local8
								(cond 
									((> local17 0) (localproc_000c 23 68))
									((== gamePhase 0) (localproc_000c 23 69))
									((>= gamePhase 1)
										(if
										(or (== (mod (++ local14) 2) 1) (< local14 2))
											(if (> local14 1)
												(localproc_000c 23 64)
											else
												(localproc_000c 23 70)
											)
											(= local15 1)
											(SolvePuzzle 2 117)
											(jailerScript changeState: 7)
										else
											(localproc_000c 23 66)
										)
									)
								)
							)
							((or local3 local4)
								(if local5
									(localproc_000c 23 71)
								else
									(localproc_000c 23 28)
								)
							)
							(else (localproc_000c 23 26))
						)
					)
					((Said 'display,flash/badge')
						(cond 
							(local8 (localproc_000c 23 72))
							(local3
								(if local5
									(localproc_000c 23 72)
								else
									(localproc_000c 23 28)
								)
							)
							(else (localproc_000c 23 73))
						)
					)
					(
						(or
							(Said 'see,look,display,get,ask/file')
							(Said 'have<do/file')
						)
						(if local8
							(if (>= gamePhase 1)
								(localproc_000c 23 24)
							else
								(localproc_000c 23 25)
							)
						else
							(localproc_000c 23 26)
						)
					)
					((Said 'interrogate,chat/dude')
						(cond 
							(local8 (localproc_000c 23 25))
							((and local2 (or local3 local4))
								(if local5
									(localproc_000c 23 58)
								else
									(localproc_000c 23 28)
								)
							)
							(else (localproc_000c 23 26))
						)
					)
					((Said '[see,chat]/*')
						(if (> local17 0)
							(localproc_000c 23 74)
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
						(if local8
							(if (== ((inventory at: 0) owner?) 23)
								(jailerScript changeState: 13)
							else
								(localproc_000c 23 75)
							)
						else
							(localproc_000c 23 26)
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
						(if local8
							(if (== ((inventory at: 10) owner?) 23)
								(localproc_000c 23 76)
								(ego get: 10)
								(jailerScript changeState: 12)
							else
								(localproc_000c 23 77)
							)
						)
					)
					(
						(or
							(Said 'close,replace/file,file')
							(Said 'gave[<back]/file,file')
						)
						(localproc_000c 23 78)
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
				(localproc_03d8)
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
				(SetMenu 513 112 1)
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
				(if (Btst 70)
					(localproc_000c 23 79 83)
				else
					(localproc_000c 23 80 83)
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
				(witness setLoop: 0 setMotion: MoveTo 153 114 self)
			)
			(8
				(witness setLoop: 3 setMotion: MoveTo 153 112 self)
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
				(witness setLoop: 0 setMotion: 0 stopUpd:)
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
					(if (ego has: 0) (localproc_000c 23 84 83))
					(self cue:)
				else
					(self cue:)
				)
			)
			(2
				(User canInput: 1)
				(if (ego has: 0)
					(localproc_000c 23 85 83)
					(localproc_000c 23 86 83)
					(localproc_000c 23 87 83)
					(localproc_000c 23 88 83)
					(ego put: 0 23)
					(self cue:)
				else
					(self cue:)
				)
			)
			(3
				(if (ego has: 10)
					(Print 23 89 #at -1 120)
					(localproc_000c 23 90)
					(ego put: 10 23)
				)
				(if (== ((inventory at: 0) owner?) 23)
					(localproc_000c 23 91)
				)
				(cond 
					((>= gamePhase 2) (Print 23 92 #at -1 125 #draw) (= local7 1))
					((< gamePhase 1) (localproc_000c 23 93 83) (= local7 1))
				)
			)
			(4
				(jailer setMotion: MoveTo 275 129 self)
			)
			(5 (jailer stopUpd:))
			(6
				(jailer setMotion: MoveTo 248 125 startUpd:)
			)
			(7
				(HandsOff)
				(jailer setLoop: 0 setMotion: MoveTo 275 129 startUpd:)
				(= local16 30)
			)
			(8
				(jailer setLoop: 1 setMotion: MoveTo 248 125 self)
			)
			(9
				(localproc_000c 23 94 83)
				(if (== local15 0)
					(localproc_000c 23 95 83)
				else
					(localproc_000c 23 96 83)
				)
				(= local12 1)
				(SetMenu 513 112 0)
				(folderScript changeState: 0)
			)
			(10
				(HandsOff)
				(localproc_000c 23 97)
				(localproc_000c 23 98)
				(jailer setLoop: 0 setMotion: MoveTo 275 129 startUpd:)
				(= local16 20)
			)
			(11
				(jailer setLoop: -1 setMotion: MoveTo 248 125)
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
				(localproc_000c 23 99)
				(localproc_000c 23 100)
				(if (> dollars 5) (= dollars (- dollars 5)))
				(if (== ((inventory at: 10) owner?) 23)
					(localproc_000c 23 76)
					(ego get: 10)
				)
				(ego get: 0 setMotion: MoveTo 228 127 self)
			)
			(14
				(if (== gamePhase 1)
					(= gamePhase 2)
					(= captainWarningTimer 0)
				)
				(ego illegalBits: 0 setMotion: MoveTo 239 160 self)
			)
			(15
				(HandsOn)
				(ego illegalBits: -32768)
				(curRoom newRoom: 22)
			)
		)
	)
)
