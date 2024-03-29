;;; Sierra Script 1.0 - (do not remove this comment)
(script# RECORDER)
(include game.sh)
(use Print)
(use File)
(use System)

(public
	RecordInfo 0
)

(class RecordInfo of File
	(properties
		handle 0
		numTellers 0
		curTeller 0
		tellerNodes 0
		topics 0
		name "RECORDER.DAT"
	)
	
	(method (init theNumTellers)
		(super init: &rest)
		(if argc (= numTellers theNumTellers))
		(= topics 22)
	)
	
	(method (create &tmp [theTopics 2000] i)
		(if (not (self open: 2)) (return 0))
		(= i 0)
		(while (< i numTellers)
			(= [theTopics i] 0)
			(++ i)
		)
		(= i 0)
		(while (< i numTellers)
			(= [theTopics i] topics)
			(++ i)
		)
		(self write: @theTopics (* 2 numTellers))
		(= i 0)
		(while (< i topics)
			(= [theTopics i] 0)
			(++ i)
		)
		(= i 0)
		(while (< i numTellers)
			(self write: @theTopics (* 2 topics))
			(++ i)
		)
		(self close:)
		(return 1)
	)
	
	(method (copySave)
	)
	
	(method (copyRestore)
	)
	
	(method (readHeader &tmp [temp0 60] newList temp61)
		(self open: 1)
		(self seek: 0)
		(self read: @temp0 (* 2 numTellers))
		(self close:)
		(= newList (List new:))
		(= temp61 0)
		(while (< temp61 numTellers)
			(newList addToEnd: [temp0 temp61])
			(++ temp61)
		)
		(return newList)
	)
	
	(method (writeHeader param1 &tmp [temp0 60] temp60)
		(if (!= numTellers (param1 size?)) (return 0))
		(= temp60 0)
		(while (< temp60 numTellers)
			(= [temp0 temp60] (param1 at: temp60))
			(++ temp60)
		)
		(self open: 0)
		(self seek: 0)
		(self write: @temp0 (* 2 numTellers))
		(self close:)
		(return 1)
	)
	
	(method (readTeller theCurTeller &tmp newList newList_2 [temp2 2000] theTopics fileHeader temp2004 temp2005 newList_3 temp2007)
		(if argc (= curTeller theCurTeller))
		(= theTopics (* 2 numTellers))
		(= tellerNodes
			((= fileHeader (self readHeader:))
				at: curTeller
			)
		)
		(= temp2004 0)
		(while (< temp2004 curTeller)
			(= theTopics
				(+
					theTopics
					(* 2 (fileHeader at: temp2004))
				)
			)
			(++ temp2004)
		)
		(self open: 1)
		(self seek: theTopics)
		(self read: @temp2 (* 2 tellerNodes))
		(self close:)
		(= newList (List new:))
		(= newList_2 (List new:))
		(= temp2004 0)
		(while (< temp2004 topics)
			(newList_2 addToEnd: [temp2 temp2004])
			(++ temp2004)
		)
		(newList addToEnd: newList_2)
		(= theTopics topics)
		(= temp2004 0)
		(while (< temp2004 topics)
			(= newList_3 0)
			(if (= temp2007 (newList_2 at: temp2004))
				(= newList_3 (List new:))
				(= temp2005 0)
				(while (< temp2005 temp2007)
					(newList_3 addToEnd: [temp2 (+ theTopics temp2005)])
					(++ temp2005)
				)
			)
			(newList addToEnd: newList_3)
			(= theTopics (+ theTopics temp2007))
			(++ temp2004)
		)
		(fileHeader dispose:)
		(return newList)
	)
	
	(method (writeTeller param1 theCurTeller &tmp [temp0 2000] temp2000 temp2001 temp2002 temp2003 temp2004 temp2005 fileHeader i temp2008 theTopics)
		(= fileHeader (self readHeader:))
		(if (> argc 1) (= curTeller theCurTeller))
		(= i (= temp2000 0))
		(while (< i topics)
			(= temp2000 (+ temp2000 ((param1 at: 0) at: i)))
			(++ i)
		)
		(if
			(<=
				(= temp2001
					(-
						(= temp2000 (+ temp2000 topics))
						(fileHeader at: curTeller)
					)
				)
				0
			)
			(fileHeader dispose:)
			(return 0)
		)
		(= temp2005 (* 2 numTellers))
		(= temp2003 (- numTellers 1))
		(= i 0)
		(while (< i temp2003)
			(= temp2005
				(+ temp2005 (* 2 (fileHeader at: i)))
			)
			(++ i)
		)
		(self open: 0)
		(= i temp2003)
		(= temp2004 (fileHeader at: i))
		(while (> i curTeller)
			(if (not temp2004)
				(= temp2005 (+ temp2005 (* 2 temp2001)))
			else
				(self seek: temp2005)
				(self read: @temp0 (* 2 temp2004))
				(self seek: (= temp2005 (+ temp2005 (* 2 temp2001))))
				(self write: @temp0 (* 2 temp2004))
			)
			(-- i)
			(= temp2005
				(-
					temp2005
					(*
						2
						(+
							temp2001
							(= temp2004 (fileHeader at: i))
						)
					)
				)
			)
		)
		(= i 0)
		(while (< i topics)
			(= [temp0 i] ((param1 at: 0) at: i))
			(++ i)
		)
		(= theTopics topics)
		(= i 1)
		(while (<= i topics)
			(if (param1 at: i)
				(if
					(!=
						(= temp2002 ((param1 at: i) size?))
						((param1 at: 0) at: (- i 1))
					)
					(Printf {Recorder data corrupt at index: %d} i)
				)
				(= temp2008 0)
				(while (< temp2008 temp2002)
					(= [temp0 theTopics]
						((param1 at: i) at: temp2008)
					)
					(++ temp2008)
					(++ theTopics)
				)
			)
			(++ i)
		)
		(self seek: temp2005)
		(self write: @temp0 (* 2 temp2000))
		(= i 0)
		(while (< i numTellers)
			(= [temp0 i]
				(fileHeader at: i)
			)
			(++ i)
		)
		(fileHeader dispose:)
		(= [temp0 curTeller] temp2000)
		(self seek: 0)
		(self write: @temp0 (* 2 numTellers))
		(self close:)
		(return 1)
	)
)
